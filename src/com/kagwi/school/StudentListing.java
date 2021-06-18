package com.kagwi.school;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
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
	private String[] columnNames = { "ID", "FULL NAME", "NATIONALITY", "PHONE", "EMAIL", "GRADES" };

	private DBOperations dbOperations;

	public StudentListing() throws ClassNotFoundException, SQLException {
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

		dbOperations = new DBOperations();

		studentTable = new JTable();
		studentTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					int row = studentTable.getSelectedRow();
					int column = studentTable.getSelectedColumn();

					String result = studentTable.getValueAt(row, column).toString();
					String id = studentTable.getValueAt(row, 0).toString();
					update(id, result, column);
				}
			}
		});

		scrollPane.setViewportView(studentTable);
		studentTable.setModel(model);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setFillsViewportHeight(true);
		setTitle("Database results");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Closed");
				e.getWindow().dispose();
			}
		});
	}

	public void update(String id, String result, int column) {
		System.out.println("Column " + column + " has been changed to " + result);
		dbOperations.updateStudent(getColumnName(column), id, result);
	}

	public ArrayList<StudentModel> showStudents() {
		ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();
		studentList = dbOperations.getAllStudents();

		int i = 0;
		while (i != studentList.size()) {
			model.addRow(new Object[] { studentList.get(i).getId(), studentList.get(i).getFullName(),
					studentList.get(i).getNationality(), studentList.get(i).getPhone(), studentList.get(i).getEmail(),
					studentList.get(i).getGrades() });
			i++; // Increment value
		}
		return studentList;
	}

	public String getColumnName(int i) {

		switch (i) {
		case 1:
			return "fullname";
		case 2:
			return "nationality";
		case 3:
			return "phone";
		case 4:
			return "email";
		case 5:
			return "grades";
		default:
			return null;

		}
	}

}
