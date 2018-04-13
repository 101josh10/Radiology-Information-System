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

public class LoginController 
{

	@FXML private ChoiceBox<String> viewSelect;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private Button clickLogin;
	
	@FXML Stage stage;
	@FXML Parent root;
	@FXML Scene scene;
	
	Connection conn = DBConnection.dbConnection();
	boolean status;
	
	public void Login(ActionEvent event) throws Exception
	{
		viewSelect.getItems().add("Radiologist");
		viewSelect.getItems().add("Technician");
		viewSelect.getItems().add("Receptionist");
		viewSelect.getItems().add("MD");
		if(viewSelect.getValue() == "Radiologist") 
		{
			String query = "SELECT * FROM RadLogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );

			ResultSet rs = conn.prepareStatement(query).executeQuery();
			
			if (rs.next())
			{
				stage = (Stage) txtPassword.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("/view/RadiologistView.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				
				stage.show();
			}
			
		}
		
		else if(viewSelect.getValue() == "Technician") 
		{
			String query = "SELECT * FROM TechLogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );
			status = pst.execute();
			if (status) 
			{
				System.out.println("Login Successful!");
			}
		}
		else if(viewSelect.getValue() == "Receptionist")
		{
			String query = "SELECT * FROM FrontLogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );
			status = pst.execute();
			if (status) 
			{
				System.out.println("Login Successful!");
			}
		}
		else if(viewSelect.getValue() == "MD")
		{
			String query = "SELECT * FROM MDLogin WHERE username = ? and password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, txtUsername.getText() );
			pst.setString(2, txtPassword.getText() );
			status = pst.execute();
			if (status) 
			{
				System.out.println("Login Successful!");
			}
		}
	}
	
	
	public void initialize(URL location, ResourceBundle recources) 
	{
		throw new UnsupportedOperationException("Not Supported.");
	}
	
}


