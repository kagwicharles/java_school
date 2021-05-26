package com.kagwi.school;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DatabaseCredentials extends JFrame {
	private JTextField databaseName;
	private JTextField databaseUser;
	private JTextField databaseHost;
	private JTextField databasePort;
	private JPasswordField databasePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseCredentials frame = new DatabaseCredentials();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatabaseCredentials() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 402);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Database Name:");
		lblNewLabel_1.setBounds(12, 60, 131, 15);
		getContentPane().add(lblNewLabel_1);
		
		databaseName = new JTextField();
		databaseName.setBounds(161, 58, 124, 19);
		getContentPane().add(databaseName);
		databaseName.setColumns(10);
		
		JLabel lblDatabaseUser = new JLabel("Database User:");
		lblDatabaseUser.setBounds(12, 192, 112, 15);
		getContentPane().add(lblDatabaseUser);
		
		databaseUser = new JTextField();
		databaseUser.setBounds(161, 188, 124, 19);
		getContentPane().add(databaseUser);
		databaseUser.setColumns(10);
		
		JLabel lblDatabasePass = new JLabel("Database pass:");
		lblDatabasePass.setBounds(12, 241, 112, 15);
		getContentPane().add(lblDatabasePass);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(12, 115, 66, 15);
		getContentPane().add(lblHost);
		
		databaseHost = new JTextField();
		databaseHost.setBounds(161, 113, 124, 19);
		getContentPane().add(databaseHost);
		databaseHost.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(12, 142, 66, 19);
		getContentPane().add(lblPort);
		
		databasePort = new JTextField();
		databasePort.setBounds(161, 144, 124, 19);
		getContentPane().add(databasePort);
		databasePort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormData();
				new DBOperations().createTable();
				openStudentForm();
				dispose();
			}
		});
		
		btnConnect.setBounds(374, 328, 114, 25);
		getContentPane().add(btnConnect);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(243, 328, 114, 25);
		getContentPane().add(btnCancel);
		
		databasePass = new JPasswordField();
		databasePass.setBounds(161, 239, 124, 19);
		getContentPane().add(databasePass);
		setTitle("Database Credentials");
	}
	
	public void openStudentForm() {
	
			StudentForm frame = new StudentForm();
		frame.setVisible(true);
		
	}
	
	private void getFormData() {
		String dbName = databaseName.getText().toString();
		String dbHost = databaseHost.getText().toString();
		String dbPort = databasePort.getText().toString();
		String dbUser = databaseUser.getText().toString();
		String dbPass = databasePass.getText().toString();
		try {
			new DBConnect(dbHost, dbName, dbUser, dbPass, dbPort).getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
