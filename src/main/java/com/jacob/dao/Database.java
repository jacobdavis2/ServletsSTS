package com.jacob.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	static Connection connection;
	
	public static boolean openConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			
			// Oracle would use: 
			//Class.forName("java.sql.Driver");
			
			connection = DriverManager.getConnection("jdbc:postgresql://drona.db.elephantsql.com:5432/pelefvez", "pelefvez", "YabO1-tY1u781YXgREQK21TMwS2fRC9p");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				
				return connection.isClosed();
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
