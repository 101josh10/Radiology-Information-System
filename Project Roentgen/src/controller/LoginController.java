package controller;

import model.DBConnection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class LoginController implements Initializable
{

	@FXML private ChoiceBox<String> viewSelect;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Button clickLogin;
	@FXML private Button clickRegister;
	
	@FXML Stage stage;
	@FXML Parent root;
	@FXML Scene scene;
	
	Connection conn = DBConnection.dbConnection();
	boolean status;
	
	public void Login(ActionEvent event) throws Exception
	{
		if(viewSelect.getValue() == "Radiologist") 
		{
			String query = "SELECT * FROM radlogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );

			ResultSet rs = pst.executeQuery();
			
			if (rs.next())
			{
				System.out.println("Login Successful!");
				stage = (Stage) txtPassword.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("../view/RadiologistView.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				
				stage.show();
			}
		}
		else if(viewSelect.getValue() == "Technician") 
		{
			String query = "SELECT * FROM techlogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );

			ResultSet rs = pst.executeQuery();
			
			if (rs.next())
			{
				System.out.println("Login Successful!");
				stage = (Stage) txtPassword.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("../view/technician.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				
				stage.show();
			}
		}
		else if(viewSelect.getValue() == "Receptionist")
		{
			String query = "SELECT * FROM frontlogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next())
			{
				System.out.println("Login Successful!");
				stage = (Stage) txtPassword.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("../view/ReceptionistView.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				
				stage.show();
			}
		}
		else if(viewSelect.getValue() == "MD")
		{
			String query = "SELECT * FROM mdlogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );

			ResultSet rs = pst.executeQuery();
			
			if (rs.next())
			{
				System.out.println("Login Successful!");
				stage = (Stage) txtPassword.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("../view/MDView.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				
				stage.show();
			}
		}
	}
	public void register(ActionEvent event) throws Exception
	{
		root = FXMLLoader.load(getClass().getResource("../view/RegisterView.fxml"));
		stage = (Stage) clickRegister.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		
		stage.show();
	}
	
	
	public void initialize(URL location, ResourceBundle recources) 
	{
		viewSelect.getItems().add("Radiologist");
		viewSelect.getItems().add("Technician");
		viewSelect.getItems().add("Receptionist");
		viewSelect.getItems().add("MD");
	}
	
}


