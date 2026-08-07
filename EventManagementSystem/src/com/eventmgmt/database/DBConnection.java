package com.eventmgmt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/eventmgmt_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";  // Update your MySQL username
	private static final String PASSWORD = "root";  //Replace with your actual MySQL root password
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// Load the JDBC driver once when the class is first used
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found! Ensure the JDBC JAR is added to the build path.");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
