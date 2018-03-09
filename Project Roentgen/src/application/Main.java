package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			/*TabPane root = new TabPane();
			Scene scene = new Scene(root,972,538);
			scene.getStylesheets().add(getClass().getResource("../view/RadiologistView.fxml").toExternalForm());
			stage.setTitle("RIS Radiologist View");
			stage.setScene(scene);
			stage.show();*/
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/RadiologistView.fxml"));
			Scene scene = new Scene(root, 972, 538);
			stage.setTitle("RIS Radiologist View");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
