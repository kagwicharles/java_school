package com.kagwi.school;

public class StudentModel {
	
	private int id;
	private String fullName;
	private String nationality;
	private String phone;
	private String email;
	private String grades;
	
	public StudentModel(int id, String fullName, String nationality, String phone, String email, String grades) {
		this.id = id;
		this.fullName = fullName;
		this.nationality = nationality;
		this.phone = phone;
		this.email = email;
		this.grades = grades;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getGrades() {
		return grades;
	}

}
