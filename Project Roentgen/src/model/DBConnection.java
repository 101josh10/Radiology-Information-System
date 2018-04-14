package model;

import java.sql.*;

import application.Main;

public class DBConnection 
{
	//public static final String DBPASSWORD = "Chrome777";
	Connection conn = null;
	
	public static Connection dbConnection(){
		System.out.println("Establishing connection to database");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/ris","root", "");
			System.out.println("Connection Successful");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
