package com.kagwi.school;

public class DBSchema {

	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS students (\n" + 
			"    id serial PRIMARY KEY, fullname VARCHAR(100), nationality VARCHAR(100), phone VARCHAR(10), email VARCHAR(100), grades VARCHAR(300));";
	private static final String QUERY_ALL_STUDENTS = "SELECT* FROM students";
	
	public DBSchema() {
		
	}
	
	public static String insertStudent() {
		String query = "INSERT INTO students(id, fullname, nationality, phone, email, grades)"
				+ " VALUES(DEFAULT, ?, ?, ?, ?, ?);";
		return query;
	}

	public static String getQueryAllStudents() {
		return QUERY_ALL_STUDENTS;
	}

	public static String getCreateTable() {
		return CREATE_TABLE;
	}
	
	
}
