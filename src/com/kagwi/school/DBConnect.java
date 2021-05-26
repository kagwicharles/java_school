package com.kagwi.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

		private static Connection connection = null;
		private static String host = "";
		private static String db = "";
		private static String user = ""; //Database user
	    private static String password = ""; //Database pass
		private static String url = ""; //Database url*/
		private static String port;
	    
		public DBConnect(String host, String db, String user, String pass, String port)
		{
		
			/*Initialize variables to connect 
			to database here*/
			this.host = host; //Domain name or Ip address of host site
			this.db = db;	//Database name
			this.user = user; //Database user
			this.password = pass; //Database password
			this.port = port; //Port for database
		}
		
		public static Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
			url = "jdbc:postgresql://"+host+":"+port+"/"+db;
			if (connection == null)
				connection = DriverManager.getConnection(url, user, password);
			
			if(connection != null)
				System.out.println("Connected successfuly");
			return connection;
		}
		
		public static void closeConnection() throws SQLException {
			if (connection != null)
				connection.close();
		}

}
