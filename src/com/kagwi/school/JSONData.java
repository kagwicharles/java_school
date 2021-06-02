package com.kagwi.school;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONData {
	
	public JSONData() {
		
	}
	
	public void putStudents(ArrayList<StudentModel> students) {
		ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();
		studentList = students;
	    JSONObject studentDetails = new JSONObject();

		int i = 0;
		while (i != studentList.size()) {
			studentDetails.put("Full Name", studentList.get(i).getFullName());
			studentDetails.put("Nationality", studentList.get(i).getNationality());
			i++;
		}
		
		JSONArray studentArray = new JSONArray();
		studentArray.add(studentDetails);
		 //Write JSON file
		File myObj = new File("/home/Desktop/students.json");
	    try {
			myObj.createNewFile();
	    	FileWriter file = new FileWriter("/home/Desktop/students.json");
	        //We can write any JSONArray or JSONObject instance to the file
	        file.write(studentArray.toJSONString()); 
	        file.flush();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
