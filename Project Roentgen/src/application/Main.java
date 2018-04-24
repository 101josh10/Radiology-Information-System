package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.DBConnection;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Connection con;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
			Scene scene = new Scene(root, 300, 350);//should match the above view's dimensions **look in scene builder to find dimensions**
			//Scene scene = new Scene(root);
			stage.setTitle("Radiology Information System");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBConnection con = new DBConnection();
		Thread t = new Thread();
		t.start();
		launch(args);
	}
}
