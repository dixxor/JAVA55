package com.customer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

	private static String dburl = "jdbc:mysql://localhost:3306/supermarket";
	private static String dbuser = "root";
	private static String dbpass = "missaka12";
	private static String dbdriver = "com.mysql.jdbc.Driver";
	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			Class.forName(dbdriver);
			
			con = DriverManager.getConnection(dburl, dbuser, dbpass);
			
		}catch(Exception e) {
			System.out.println("Database connection is not success");
		}
		
		return con;
	}
	
}
