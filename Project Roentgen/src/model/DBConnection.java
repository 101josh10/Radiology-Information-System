package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.Main;

public class DBConnection implements Runnable {
	public static final String DBPASSWORD = "Chrome777";
	Connection Con1;
	
	public void run(){
		System.out.println("Establishing connection to database");
		
		try {
			Con1 = DriverManager.getConnection("jdbc:mysql://50.62.209.15:3306","wesmdonald", DBPASSWORD);
			System.out.println("Connection success!");
			
			Main.con = Con1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//replace with actual data
	}
}
