package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	private static final String USERNAME = "wesmdonald";
	private static final String PASSWORD = "Chrome777";
	private static final String CONN = ("jdbc:mysql://50.62.209.15:3306");
	private static final String DB = "ris";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection((CONN + DB),USERNAME, PASSWORD);
	}

	public static void displayException(SQLException ex){
		System.out.println("Error Message: " + ex.getMessage());
		System.out.println("Error Code: " + ex.getErrorCode());
		System.out.println("SQL Status: " + ex.getSQLState());
	}
}
