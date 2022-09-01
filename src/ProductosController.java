import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProductosController {
    String estadoCliente,query;
    int codigoProducto;
    Conexion con = new Conexion();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtMinima;

    @FXML
    private TextField txtMaxima;

    @FXML
    private TextField txtBodega;

    @FXML
    private Button btnNuevo;

    @FXML
    private TextField txtMSG;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnActualizar;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtPrecioVenta;

    @FXML
    void buscarClick(MouseEvent  event) throws SQLException {
        
        String iden = txtNombre.getText();
        if (iden==null || iden.isEmpty()) {
            txtMSG.setText("Digite un nombre valido para buscar");
        }
        else{
            con.conectar();
            try(Statement stm = con.getCon().createStatement()){
                String ident = txtNombre.getText();
                ResultSet rta = stm.executeQuery("select * from productos where nombre = '"+ident+"'");
                if(rta.next()){
                	codigoProducto = rta.getInt("codigo");
                	txtPrecioCompra.setText(rta.getDouble("pCompra")+"");
                	txtPrecioVenta.setText(rta.getDouble("pVenta")+"");
                	txtBodega.setText(rta.getInt("cBodega")+"");
                	txtMinima.setText(rta.getInt("cMinima")+"");
                	txtMaxima.setText(rta.getInt("cmaxPer")+"");
                    if (estadoCliente.equalsIgnoreCase("A")){
                        btnBorrar.setText("Borrar");
                    } else{
                        btnBorrar.setText("Recuperar");
                    }
      
                    btnBorrar.setDisable(false);
                    btnActualizar.setDisable(false);
                    txtMSG.clear();
                }
                else
                    txtMSG.setText("No se encontro registro que coincida con el nombre");
            }
            con.desconectar();
            System.out.println("funciona buscar click");
        }

    }

    @FXML
    void clickActualizar(MouseEvent event) throws SQLException {
        System.out.println("funciona boton de actualizar");
        String nom = txtNombre.getText();
        String pCom = txtPrecioCompra.getText();
        String pVen = txtPrecioVenta.getText();
        String cBo = txtBodega.getText();
        String cMin = txtMinima.getText();
        String cMax = txtMaxima.getText();
        if(nom==null || nom.isEmpty())
            txtMSG.setText("Debe ingresar un producto valido");
        else if(pCom==null || pCom.isEmpty())
            txtMSG.setText("Debe ingresar precio de compra");
        else if(pVen==null || pVen.isEmpty())
            txtMSG.setText("Debe ingresar precio de venta");
        else if(cBo==null || cBo.isEmpty())
            txtMSG.setText("Debe ingresar cantidad en bodega");
        else if(cMin==null || cMin.isEmpty())
            txtMSG.setText("Debe ingresar una cantidad minima");
        else if(cMax==null || cMax.isEmpty())
            txtMSG.setText("Debe ingresar una cantidad maxima");
        else{
            String query1 = "UPDATE productos set nombre = '"+nom+"', pCompra = '"+Double.parseDouble(pCom) +"' , pVenta = '"+Double.parseDouble(pVen)+"', cBodega = '"+cBo+"',cMinima ='"+cMin+"',cMaxPer ='"+cMax+"' where codigo = "+codigoProducto;
            con.conectar();
            System.out.println("FUNCIONA QUERY1");
            try(Statement stm = con.getCon().createStatement()){
  
            int res = stm.executeUpdate(query1);
            if(res!=0){
                txtMSG.setText("Registro Actualizado con exito");
            }else
                txtMSG.setText("Error al Actualizar registro");
            restaurarDatos();
            } 
            con.desconectar();
        }

    }

    @FXML
    void clickBorrar(MouseEvent event) throws SQLException {
        System.out.println("funciona boton de borrar");

        query = "DELETE FROM `inventario490`.`productos` WHERE (`codigo` = '"+codigoProducto+"')";
   
        con.conectar();
        try(Statement stm = con.getCon().createStatement()){

        int res = stm.executeUpdate(query);
        if(res!=0){
            txtMSG.setText("Registro Borrado con exito");
        }else
            txtMSG.setText("Error al Borrar registro");
        restaurarDatos();
        }
        con.desconectar();
    }
    
    private void restaurarDatos(){
        
        txtNombre.clear();
        txtPrecioCompra.clear();
        txtPrecioVenta.clear();
        txtBodega.clear();
        txtMinima.clear();
        txtMaxima.clear();
        btnBorrar.setDisable(true);
        btnBorrar.setText("Borrar");
        btnActualizar.setDisable(true);
    }

    @FXML
    void clickNuevo(MouseEvent event) throws SQLException {

        System.out.println("funciona boton de nuevoo");
        String nom = txtNombre.getText();
        String pCom = txtPrecioCompra.getText();
        String pVen = txtPrecioVenta.getText();
        String cBode = txtBodega.getText();
        String cMin = txtMinima.getText();
        String cMax = txtMaxima.getText();

        if(nom==null || nom.isEmpty())
            txtMSG.setText("Debe ingresar un producto valido");
        else if(pCom==null || pCom.isEmpty())
            txtMSG.setText("Debe ingresar precio de compra");
        else if(pVen==null || pVen.isEmpty())
            txtMSG.setText("Debe ingresar precio de venta");
        else if(cBode==null || cBode.isEmpty())
            txtMSG.setText("Debe ingresar cantidad en bodega");
        else if(cMin==null || cMin.isEmpty())
            txtMSG.setText("Debe ingresar una cantidad minima");
        else if(cMax==null || cMax.isEmpty())
            txtMSG.setText("Debe ingresar una cantidad maxima");
        else{
            String query1 = "insert into productos (nombre,pCompra,pVenta,cBodega,cMinima,cMaxPer)values('"+nom+"','"+Double.parseDouble(pCom)+"','"+Double.parseDouble(pVen)+"','"+cBode+"','"+cMin+"','"+cMax+"')";
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int res = stm.executeUpdate(query1);
                if(res != 0){
                    txtMSG.setText("Datos Registrados con exito");
                    restaurarDatos();
                }
                else{
                    txtMSG.setText("Error al grabar los datos por favor verifique");
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
    }

}


