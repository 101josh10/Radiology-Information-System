package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBConnection;

public class RegisterController implements Initializable
{
	@FXML private TextField txtName;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private PasswordField txtAdmin;
	@FXML private Button clickRegister;
	@FXML private Button clickGoBack;
	@FXML private ChoiceBox<String> selectView;
	
	@FXML Stage stage;
	@FXML Parent root;
	@FXML Scene scene; 
	
	Connection conn = DBConnection.dbConnection();
	
	public void Register(ActionEvent event) throws Exception
	{
		if(txtAdmin.getText() == "Admin") 
		{
			if(selectView.getValue() == "Radiologist")
			{
				try
				{
					 String query2 = ("SELECT username from radlogin where username = ?");
					 PreparedStatement pst2 = conn.prepareStatement(query2);
					 pst2.setString(1, txtUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
							System.out.println("Username already exists.");
							return;
					 }
				}
				catch(Exception ex)
				{
					  ex.printStackTrace();  
				}
				try
				{
					String query = "INSERT INTO radlogin (name, username, password) VALUES (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtName.getText() );
					pst.setString(2, txtUsername.getText() );
					pst.setString(3, txtPassword.getText() );
		
					ResultSet rs = pst.executeQuery();
					
					if (rs.next())
					{
						System.out.println("Registration Successful!");
						stage = (Stage) txtPassword.getScene().getWindow();
						root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
						scene = new Scene(root);
						stage.setScene(scene);
						
						stage.show();
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(selectView.getValue() == "Receptionist")
			{
				try
				{
					 String query2 = ("SELECT username from frontlogin where username = ?");
					 PreparedStatement pst2 = conn.prepareStatement(query2);
					 pst2.setString(1, txtUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
							System.out.println("Username already exists.");
							return;
					 }
				}
				catch(Exception ex)
				{
					  ex.printStackTrace();  
				}
				try
				{
					String query = "INSERT INTO frontlogin (name, username, password) VALUES (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtName.getText() );
					pst.setString(2, txtUsername.getText() );
					pst.setString(3, txtPassword.getText() );
		
					ResultSet rs = pst.executeQuery();
					
					if (rs.next())
					{
						System.out.println("Registration Successful!");
						stage = (Stage) txtPassword.getScene().getWindow();
						root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
						scene = new Scene(root);
						stage.setScene(scene);
						
						stage.show();
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(selectView.getValue() == "Technician")
			{
				try
				{
					 String query2 = ("SELECT username from techlogin where username = ?");
					 PreparedStatement pst2 = conn.prepareStatement(query2);
					 pst2.setString(1, txtUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
							System.out.println("Username already exists.");
							return;
					 }
				}
				catch(Exception ex)
				{
					  ex.printStackTrace();  
				}
				try
				{
					String query = "INSERT INTO techlogin (name, username, password) VALUES (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtName.getText() );
					pst.setString(2, txtUsername.getText() );
					pst.setString(3, txtPassword.getText() );
		
					ResultSet rs = pst.executeQuery();
					
					if (rs.next())
					{
						System.out.println("Registration Successful!");
						stage = (Stage) txtPassword.getScene().getWindow();
						root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
						scene = new Scene(root);
						stage.setScene(scene);
						
						stage.show();
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(selectView.getValue() == "MD")
			{
				try
				{
					 String query2 = ("SELECT username from mdlogin where username = ?");
					 PreparedStatement pst2 = conn.prepareStatement(query2);
					 pst2.setString(1, txtUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
							System.out.println("Username already exists.");
							return;
					 }
				}
				catch(Exception ex)
				{
					  ex.printStackTrace();  
				}
				try
				{
					String query = "INSERT INTO mdlogin (name, username, password) VALUES (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtName.getText() );
					pst.setString(2, txtUsername.getText() );
					pst.setString(3, txtPassword.getText() );
		
					ResultSet rs = pst.executeQuery();
					
					if (rs.next())
					{
						System.out.println("Registration Successful!");
						stage = (Stage) txtPassword.getScene().getWindow();
						root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
						scene = new Scene(root);
						stage.setScene(scene);
						
						stage.show();
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		else 
		{
			System.out.println("Admin Password Required");
		}
	}
	
	public void GoBack(ActionEvent event) throws Exception 
	{
		root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		stage = (Stage) clickGoBack.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		
		stage.show();
	}
	
	public void initialize(URL location, ResourceBundle recources) 
	{
		selectView.getItems().add("Radiologist");
		selectView.getItems().add("Technician");
		selectView.getItems().add("Receptionist");
		selectView.getItems().add("MD");
	}
}
