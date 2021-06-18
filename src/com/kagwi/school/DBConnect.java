package com.kagwi.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private Connection connection = null;
	private String host = "";
	private String db = "";
	private String user = ""; // Database user
	private String password = ""; // Database pass
	private String url = ""; // Database url*/
	private String port;

	public DBConnect(String host, String db, String user, String pass, String port) {

		this.host = host; // Domain name or Ip address of host site
		this.db = db; // Database name
		this.user = user; // Database user
		this.password = pass; // Database password
		this.port = port; // Port for database
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
		if (connection == null)
			connection = DriverManager.getConnection(url, user, password);

		if (connection != null)
			System.out.println("Connected successfuly");
		return connection;
	}

	public void closeConnection() throws SQLException {
		if (connection != null)
			connection.close();
	}

}
