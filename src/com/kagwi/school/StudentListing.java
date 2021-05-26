package com.kagwi.school;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentListing extends JFrame {

	private JPanel contentPane;
	private JTable studentTable;
	private DefaultTableModel model;
    private String[] columnNames = {"ID", "FULL NAME", "NATIONALITY", "PHONE", "EMAIL"};


	/**
	 * Create the frame.
	 */
	public StudentListing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 630, 366);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
		
		studentTable = new JTable();
		scrollPane.setViewportView(studentTable);
		studentTable.setModel(model);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setFillsViewportHeight(true);
		setTitle("Database results");
	}
	
	 public ArrayList<StudentModel> showStudents() {
			ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();
			studentList = new DBOperations().getAllStudents();
			
	        int i = 0;
	        while (i != studentList.size()) {
				model.addRow(new Object[]{studentList.get(i).getId(), 
					studentList.get(i).getFullName(), studentList.get(i).getNationality(),
				studentList.get(i).getPhone(), studentList.get(i).getEmail()});
				i++; //Increment value
	        }
	        return studentList;
	 }

}
