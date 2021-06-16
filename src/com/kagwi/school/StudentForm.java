package com.kagwi.school;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class StudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField fullname;
	private JTextField nationality;
	private JTextField phone;
	private JTextField email;
	private JTextField mathsGrade;
	private JTextField kiswGrade;
	private JTextField engGrade;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentForm() throws ClassNotFoundException, SQLException {
		
		new DBOperations().createTable(); //Creates database table if does not exist
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 466);
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
		btnNewButton.setBounds(304, 338, 114, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registerStudent();
					viewAllStudents();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(459, 338, 114, 25);
		contentPane.add(btnNewButton_1);

		JButton btnViewAll = new JButton("View all (JTable)");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewAllStudents();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnViewAll.setBounds(29, 338, 186, 25);
		contentPane.add(btnViewAll);

		JLabel lblGrades = new JLabel("Grades");
		lblGrades.setBounds(53, 211, 93, 15);
		contentPane.add(lblGrades);
		lblGrades.setFont(new Font("Serif", Font.BOLD, 18));

		JLabel lblMathematics = new JLabel("Mathematics");
		lblMathematics.setBounds(101, 238, 114, 19);
		contentPane.add(lblMathematics);

		JLabel lblKiswahili = new JLabel("Kiswahili");
		lblKiswahili.setBounds(101, 269, 96, 15);
		contentPane.add(lblKiswahili);

		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setBounds(101, 296, 70, 15);
		contentPane.add(lblEnglish);

		mathsGrade = new JTextField();
		mathsGrade.setBounds(224, 238, 124, 19);
		contentPane.add(mathsGrade);
		mathsGrade.setColumns(10);

		kiswGrade = new JTextField();
		kiswGrade.setBounds(224, 267, 124, 19);
		contentPane.add(kiswGrade);
		kiswGrade.setColumns(10);

		engGrade = new JTextField();
		engGrade.setBounds(224, 294, 124, 19);
		contentPane.add(engGrade);
		engGrade.setColumns(10);

		JLabel lblBioData = new JLabel("Bio Data");
		lblBioData.setBounds(57, 26, 110, 15);
		contentPane.add(lblBioData);
		lblBioData.setFont(new Font("Serif", Font.BOLD, 18));

		JButton btnViewAllweb = new JButton("View all (Web View)");
		btnViewAllweb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							new SocketConnect().launchInWeb();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			}
		});
		btnViewAllweb.setBounds(29, 392, 186, 25);
		contentPane.add(btnViewAllweb);
		setTitle("Student registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void registerStudent() throws ClassNotFoundException, SQLException {
		
		String name = fullname.getText().toString();
		String nation = nationality.getText().toString();
		String phoneNo = phone.getText().toString();
		String e_mail = email.getText().toString();
		new DBOperations().insertStudent(name, nation, phoneNo, e_mail, createJson());
		
	}

	public String createJson() {
		
		JSONObject gradesObj = new JSONObject();
		gradesObj.put("Mathematics", mathsGrade.getText().toString());
		gradesObj.put("Kiswahili", kiswGrade.getText().toString());
		gradesObj.put("English", engGrade.getText().toString());
		return gradesObj.toString();
	}

	public void viewAllStudents() throws ClassNotFoundException, SQLException {
		StudentListing frame = new StudentListing();
		frame.setVisible(true);
		frame.showStudents();
	}
}
