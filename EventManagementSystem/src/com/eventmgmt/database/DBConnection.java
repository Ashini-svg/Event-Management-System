package com.eventmgmt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/eventmgmt_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";  // Update your MySQL username
	private static final String PASSWORD = "root";  //Replace with your actual MySQL root password
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			
		} catch (SQLException e) {
			System.err.println("Database connection failed " + e.getMessage());
		}
		return conn;
	}
}
