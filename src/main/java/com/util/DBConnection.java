package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {
	 private static Connection conn;

	    /**
	     * Retrieves a connection to the database.
	     * @return A Connection object representing the database connection.
	     */
	    public static Connection getConnection() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Entering into DB ---- ");

	        // Database URL
	        String url = "jdbc:mysql://localhost:3306/casestudy";
	        
	        // Prompting user for username and password
	        System.out.println("Enter username: ");
	        String username = scanner.next();
	        System.out.println("Enter password: ");
	        String password = scanner.next();

	        try {
	            // Establishing a connection to the database
	            conn = DriverManager.getConnection(url, username, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	            // Closing scanner to prevent resource leaks
//	            scanner.close();
	        
	        
	        return conn;
	    }

}
