package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.DBConnection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Connection con;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/ReceptionistView.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("../view/RadiologistView.fxml"));
			Scene scene = new Scene(root, 1200, 700);//should match the above view's dimensions **look in scene builder to find dimensions**
			stage.setTitle("RIS Radiologist View");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBConnection idb = new DBConnection();
		Thread t = new Thread();
		t.start();
		launch(args);
	}
}
