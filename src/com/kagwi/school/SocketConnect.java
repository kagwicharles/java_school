package com.kagwi.school;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.awt.Desktop;

public class SocketConnect {

	private ArrayList<StudentModel> students;

	public SocketConnect() throws ClassNotFoundException, SQLException {
		students = new ArrayList<StudentModel>();
		students = new DBOperations().getAllStudents();
	}

	// Method to lauch program in web
	public void launchInWeb() throws ParseException {
		openUrl(); // Open default browser
		// Start server after input
		try (ServerSocket serverSocket = new ServerSocket(9090)) { // Instantiate ServerSocket and set port
			while (true) {
				try (Socket client = serverSocket.accept()) {
					handleClient(client); // Method to show arraylist in browser
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// Method to handle client connection
	private void handleClient(Socket client) throws IOException, ParseException {

		PrintWriter out = new PrintWriter(client.getOutputStream());
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: text/html");
		out.println("\r\n");

		out.println("<html>");
		out.println("<head> <style> table, th, td {\n" + "  border: 1px solid #012237;\n" + "} </style></head>");
		out.println("<body>");
		out.println("<h1>Welcome, Kagwi<h2>");
		out.println("<h3>All students</h3>");
		out.println("<table>");
		out.println(
				"<tr> <th> Id </th> <th> Full Name </th> <th> Nationality </th> <th> Phone </th> <th> Grades </th> <th> Average </th></tr>");

		// This loop traverses the students list and outputs them in browser
		int i = 0;
		while (i < students.size()) {

			System.out.println("All students " + students.get(i).getFullName());
			int currentStudent = i + 1;
			out.println("<tr>");
			out.println("<td>" + String.valueOf(currentStudent) + "</td>");
			out.println("<td>" + students.get(i).getFullName() + "</td>");
			out.println("<td>" + students.get(i).getNationality() + "</td>");
			out.println("<td>" + students.get(i).getPhone() + "</td>");
			out.println("<td>" + students.get(i).getGrades() + "</td>");
			out.println("<td>" + calcuateAvgMarks(students.get(i).getGrades()) + "</td>");
			out.println("</tr>");
			i++;
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close(); // Close output stream
		client.close(); // Close client socket
	}

	// Method to return average grade computed as string
	public String calcuateAvgMarks(String marks) throws ParseException {

		Object obj = new JSONParser().parse(marks);
		JSONObject jo = (JSONObject) obj;
		int maths = Integer.parseInt(jo.get("Mathematics").toString());
		int kiswahili = Integer.parseInt(jo.get("Kiswahili").toString());
		int english = Integer.parseInt(jo.get("English").toString());
		float avgMarks = (maths + kiswahili + english) / 3;
		return String.valueOf(avgMarks);
	}

	// This method opens url on the default browser
	public static void openUrl() {
		String url = "http://localhost:9090";

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
