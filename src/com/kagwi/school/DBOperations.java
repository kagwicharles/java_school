package com.kagwi.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperations {
	
	private Connection connection;
	private static PreparedStatement pst;
	private Statement st;
	private static ResultSet rs;
	
	public DBOperations() throws ClassNotFoundException, SQLException {
		if (connection == null)
			connection = SingletonDBConnect.getInstance().getDBConnection();
	}
		
	public void createTable() {
		try {
			st = connection.createStatement();
			st.executeUpdate(DBSchema.getCreateTable());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("TABLE CREATION UNSUCCESSFUL");
		}
	}
	
	public void insertStudent(String fullname, String nationality, String phone, String email, String grades) {
		try {
			pst = connection.prepareStatement(DBSchema.insertStudent());
			pst.setString(1, fullname);
            pst.setString(2, nationality);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, grades);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(String columnName, String condition, String newValue) {
		try {
			pst = connection.prepareStatement(DBSchema.updateStudent(columnName));
            pst.setString(1, newValue);
            pst.setInt(2, Integer.parseInt(condition));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StudentModel> getAllStudents() {
		ArrayList<StudentModel> students = new ArrayList<>();
		try {
			pst = connection.prepareStatement(DBSchema.getQueryAllStudents());
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String nationality = rs.getString(3);
				String phone = rs.getString(4);
				String email = rs.getString(5);
				String grades = rs.getString(6);
				students.add(new StudentModel(id, fullname, nationality, phone, email, grades));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

}
