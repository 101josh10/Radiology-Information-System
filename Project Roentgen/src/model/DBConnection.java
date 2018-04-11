package model;

import java.sql.*;

import application.Main;

public class DBConnection 
{
	public static final String DBPASSWORD = "Chrome777";
	Connection Con1 = null;
	
	public static Connection dbConnection(){
		System.out.println("Establishing connection to database");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection Con1 = DriverManager.getConnection("jdbc:mysql://50.62.209.15:3306/ris","wesmdonald", DBPASSWORD);
			System.out.println("Connection Successful");
			return Con1;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
