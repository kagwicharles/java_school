package com.kagwi.school;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField fullname;
	private JTextField nationality;
	private JTextField phone;
	private JTextField email;

	/**
	 * Create the frame.
	 */
	public StudentForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setBounds(101, 53, 96, 15);
		contentPane.add(lblFullName);
		
		fullname = new JTextField();
		fullname.setBounds(224, 51, 124, 19);
		contentPane.add(fullname);
		fullname.setColumns(10);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(101, 98, 96, 15);
		contentPane.add(lblNationality);
		
		nationality = new JTextField();
		nationality.setBounds(224, 96, 124, 19);
		contentPane.add(nationality);
		nationality.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(101, 139, 96, 15);
		contentPane.add(lblPhoneNo);
		
		phone = new JTextField();
		phone.setBounds(224, 137, 124, 19);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(101, 176, 66, 15);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(224, 174, 124, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(271, 243, 114, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStudent();
				viewAllStudents();
			}
		});
		btnNewButton_1.setBounds(411, 243, 114, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnViewAll = new JButton("View all");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAllStudents();
			}
		});
		btnViewAll.setBounds(23, 243, 117, 25);
		contentPane.add(btnViewAll);
		setTitle("Student registration");
	}
	
	public void registerStudent() {
		String name = fullname.getText().toString();
		String nation = nationality.getText().toString();
		String phoneNo = phone.getText().toString();
		String e_mail = email.getText().toString();
		new DBOperations().insertStudent(name, nation, phoneNo, e_mail);
	}
	
	public void viewAllStudents() {
		StudentListing frame = new StudentListing();
		frame.setVisible(true);
		frame.showStudents();
	}
}
