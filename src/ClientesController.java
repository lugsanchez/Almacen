import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClientesController{
    String estadoCliente;
    int codigoCliente;
    Conexion con = new Conexion();;
    
    @FXML
    private TextField textIdenti;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private ComboBox<String> comboGenero;

    @FXML
    private TextField textMSG;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnActualizar;


    @FXML
    void buscarImageClick(MouseEvent event) throws SQLException {
        System.out.println("funciona imagen");
        String iden = textIdenti.getText();
        if (iden==null || iden.isEmpty()) {
            textMSG.setText("Digite una identificación valida para poder buscar");
        }
        else{
            con.conectar();
            System.out.println("funciona imagen");
            try(Statement stm = con.getCon().createStatement()){
                int ident = Integer.parseInt(textIdenti.getText());
                ResultSet rta = stm.executeQuery("select * from clientes where cedula = '"+ident+"'");
                if(rta.next()){
                    codigoCliente = rta.getInt("id");
                    estadoCliente = rta.getString("estado");
                    textNombre.setText(rta.getString("nombre"));
                    textApellido.setText(rta.getString("apellido"));
                    comboGenero.setValue(rta.getString("genero"));
                    if (estadoCliente.equalsIgnoreCase("A")) {
                        btnBorrar.setText("Borrar");
                    } else {
                        btnBorrar.setText("Recuperar");
                    }
                    btnBorrar.setDisable(false);
                    btnActualizar.setDisable(false);
                    textMSG.clear();
                }
                else
                textMSG.setText("No se encontro registro que coincida con la identificación");
            }
            con.desconectar();
        }

    }

    @FXML
    void clickActualizar(MouseEvent event) throws SQLException {
        String ide = textIdenti.getText();
        String nom = textNombre.getText();
        String ape = textApellido.getText();
        String gen = comboGenero.getValue();
        if(ide==null || ide.isEmpty())
        textMSG.setText("Debe ingresar una Identificacion valida");
        else if(nom==null || nom.isEmpty())
        textMSG.setText("Debe ingresar un Nombre valido");
        else if(ape==null || ape.isEmpty())
        textMSG.setText("Debe ingresar un Apellido valido");
        else if(gen==null || gen.isEmpty())
        textMSG.setText("Debe ingresar un genero valido");
        else{
            String query1 = "UPDATE clientes set cedula = '"+ide+"', nombre = '"+nom+"' , apellido = '"+ape+"', genero = '"+gen+"' where cedula = "+ide;
            con.conectar();
            try(Statement stm = con.getCon().createStatement()){
        
            int res = stm.executeUpdate(query1);
            if(res!=0){
                textMSG.setText("Registro Actualizado con exito");
            }else
            textMSG.setText("Error al Actualizar registro");
            restaurarDatos();
            } 
            con.desconectar();
        }
    }

    @FXML
    void clickBorrar(MouseEvent event) throws SQLException {
     
        String acc = btnBorrar.getText();
        String query;
        if ("Borrar".equalsIgnoreCase(acc)) {
            query = "UPDATE clientes set estado = 'I' where id="+codigoCliente;
        } else {
            query = "UPDATE clientes set estado = 'A' where id="+codigoCliente;
        }
        con.conectar();
        try(Statement stm = con.getCon().createStatement()){
        //Ejecuta comando de accion
        int res = stm.executeUpdate(query);
        if(res!=0){
            textMSG.setText("Registro Recuperado/Borrado con exito");
        }else
        textMSG.setText("Error al recuperar/Borrar registro");
        restaurarDatos();
        }
        con.desconectar();
    }
    private void restaurarDatos(){
        textNombre.clear();
        textApellido.clear();
        textIdenti.clear();
        comboGenero.setValue("M");
        btnBorrar.setDisable(true);
        btnBorrar.setText("Borrar");
        btnActualizar.setDisable(true);
    }

    @FXML
    void clickNuevo(MouseEvent event) throws SQLException {

        String ide = textIdenti.getText();
        String nom = textNombre.getText();
        String ape = textApellido.getText();
        String gen = comboGenero.getValue();
        if(ide==null || ide.isEmpty())
        textMSG.setText("Debe ingresar una Identificacion valida");
        else if(nom==null || nom.isEmpty())
        textMSG.setText("Debe ingresar un Nombre valido");
        else if(ape==null || ape.isEmpty())
        textMSG.setText("Debe ingresar un Apellido valido");
        else if(gen==null || gen.isEmpty())
        textMSG.setText("Debe ingresar un genero valido");
        else{
            String query1 = "insert into clientes (cedula,nombre,apellido,genero,estado)values ('"+ide+"','"+nom+"','"+ape+"','"+gen+"','A')";
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int rest = stm.executeUpdate(query1);
                if(rest != 0){
                    textMSG.setText("Datos Registrados con exito");
                    restaurarDatos();
                }
                else{
                    textMSG.setText("Error al grabar los datos por favor verifique");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            con.desconectar();
        }
    }

    @FXML
    void initialize(){ 
        btnBorrar.setDisable(true);
        btnActualizar.setDisable(true);
   
        comboGenero.getItems().clear();
        comboGenero.getItems().addAll("M", "F");
        comboGenero.setValue("seleccionar genero");
    }

}

