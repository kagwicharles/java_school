package com.kagwi.school;

import java.sql.Connection;
import java.sql.SQLException;

public class SingletonDBConnect {

	public static SingletonDBConnect instance = null;
	
	private SingletonDBConnect () {}
	
	public static SingletonDBConnect getInstance() {
		if (instance == null)
			return new SingletonDBConnect();
		return instance;
	}
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException {
		return new DBConnect("localhost", "postgres", "kagwi", "ckagwi", "5432").getConnection(); //Put your database credentials here
	}
 }
