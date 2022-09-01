import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Menu btnDatos;

    @FXML
    private MenuItem btnCliente;

    @FXML
    private Menu btnAcciones;

    @FXML
    private MenuItem btnVentas;

    @FXML
    private MenuItem btnProductos;

    @FXML
    private Menu btnExit;


    @FXML
    void VentasClick(ActionEvent event) throws IOException{
      
        Parent root = (new FXMLLoader(getClass().getResource("/Ventas.fxml"))).load();
        Scene scene = new Scene(root);
        Stage teatro = new Stage();
        teatro.setTitle("Ventas");
        teatro.setScene(scene);

        teatro.show();

        System.out.println("funciona");
    }
     
    @FXML
    void  ClienteClick(ActionEvent event) throws IOException{
      
        Parent root = (new FXMLLoader(getClass().getResource("/Clientes.fxml"))).load();
        Scene scene = new Scene(root);
        Stage teatro = new Stage();
        teatro.setTitle("Clientes");
        teatro.setScene(scene);

        teatro.show();
        System.out.println("funciona");
    }

    @FXML
    void  ProductosClick(ActionEvent event) throws IOException{
        Parent root = (new FXMLLoader(getClass().getResource("/Productos.fxml"))).load();
        Scene scene = new Scene(root);
        Stage teatro = new Stage();
        teatro.setTitle("Productos");
        teatro.setScene(scene);

        teatro.show();
        System.out.println("funciona productos");
    } 
}






    

