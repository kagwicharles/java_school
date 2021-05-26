package com.kagwi.school;

public class StudentModel {
	
	private int id;
	private String fullName;
	private String nationality;
	private String phone;
	private String email;
	
	public StudentModel(int id, String fullName, String nationality, String phone, String email) {
		this.id = id;
		this.fullName = fullName;
		this.nationality = nationality;
		this.phone = phone;
		this.email = email;
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
	

}
