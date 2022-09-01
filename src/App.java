
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	@Override
	public void start(Stage stage) throws Exception{
		try {
			 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaController/Home.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Home");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	 public static void main(String[] args) throws Exception{
		    launch(args);
		}

}


    


    

 
