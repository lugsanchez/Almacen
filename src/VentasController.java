import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class VentasController {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String dato, query;
    Conexion conect = new Conexion();

    @FXML
    private ComboBox<String> comboxCliente;

    @FXML
    private ComboBox<String> comboxProducto;

    @FXML
    private TextField textCantidad;

    @FXML
    private Button btnGuardar;

    @FXML
    void ingresarBtn(ActionEvent event) throws SQLException {
        String cliente = (String) comboxCliente.getValue();
        String producto = (String) comboxProducto.getValue();
        String cantidad = textCantidad.getText();
        if (cliente==null || cliente.isEmpty()) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un cliente");
            alerta.showAndWait();
        }
        else if(producto==null || producto.isEmpty()){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un producto");
            alerta.showAndWait();
        }
        else if(cantidad==null || cantidad.isEmpty() || !esValido(cantidad)){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un producto");
            alerta.showAndWait();
        }
        else{
            conect.conectar();
            try(Statement stm = conect.getCon().createStatement()){
                String[] tmp = cliente.split(" ");
                int clie = Integer.parseInt(tmp[0]);
                tmp = producto.split(" ");
                int prod = Integer.parseInt(tmp[0]);
                int cant = Integer.parseInt(cantidad);
                query = "INSERT INTO ventas(cliente,producto,cantidad) VALUES ("+clie+","+prod+","+cant+")";
                int resu = stm.executeUpdate(query);
                if(resu!=0){
                    System.out.println("Datos Insertados con exito");
                    textCantidad.clear();
                }
                else{
                    System.out.println("Error al registrar la venta");
                }  
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean esValido(String valor){
            boolean sw = false;
            try{
                int dato = Integer.parseInt(valor);
                sw= dato>0;
            }
            catch (NumberFormatException e){
                sw = false;
            }
            return sw;
    }
    
    @FXML
    void initialize() throws IOException, SQLException{
        //Declaro variable
        
        ResultSet rst;
        //Conectarme a la base de datos        
        conect.conectar();
        //Preparar para recuperar datos de la base de datos. Tabla clientes
        query = "SELECT id,nombre,apellido from clientes order by apellido,nombre";
        try (Statement stm = conect.getCon().createStatement()){ //Preparo el area para las consultas
            rst = stm.executeQuery(query);
            while (rst.next()) {
                dato = String.format("%d %s %s", rst.getInt("id"), rst.getString("nombre"), rst.getString("apellido"));
                comboxCliente.getItems().addAll(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Preparar para recuperar datos de la base de datos. Tabla Productos
        query = "SELECT codigo,nombre from productos order by nombre";
        try (Statement stm = conect.getCon().createStatement()){ //Preparo el area para las consultas
            rst = stm.executeQuery(query);
            while (rst.next()) {
                dato = String.format("%d %s", rst.getInt("codigo"), rst.getString("nombre"));
                comboxProducto.getItems().addAll(dato);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

