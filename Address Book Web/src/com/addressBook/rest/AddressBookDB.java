package com.addressBook.rest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressBookDB extends AddressBook{
	private String url = "jdbc:mysql://localhost:3306/contactsListDB?serverTimezone=UTC"; // url for sql server
	private String username = "root"; // localhost username
	private String password = "root"; // localhost password
	Connection myConnection  = null;

	Person person = new Person();

	public AddressBookDB (){

		System.out.println("Connection Constructor");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			// Get a connection to database
			myConnection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDataIntoSQL(){

		try {

			String sqlSave = "INSERT INTO contacts"
					+ "(firstName, lastName, phoneNumber, address, resStatus)" 
					+ "VALUES(?, ?, ?, ?, ?) ";



			// Crate a PreparedStatement (so we can take user input for select columns in our db, first name, last name, etc.)
			PreparedStatement myStatement = myConnection.prepareStatement(sqlSave);


			for(int i = 0; i < contacts.size(); i++) {

				myStatement.setString(1, contacts.get(i).getFirstName());
				myStatement.setString(2, contacts.get(i).getLastName());
				myStatement.setString(3, contacts.get(i).getPhoneNumber());
				myStatement.setString(4, contacts.get(i).getAddress());
				myStatement.setString(5, contacts.get(i).getStatus());

				myStatement.executeUpdate();

			}

			System.out.println("TESTING TO SEE IF CONTACTS WERE ADDED TO DB!");
			// Set ResultSet to execution of the statement to retrieve information requested
			ResultSet myResultSet = myStatement.executeQuery("SELECT * from contacts");

			while (myResultSet.next()) { // As long as there are contacts to retrieve in the db, retrieve them
				System.out.println(myResultSet.getString("firstName") 
						+ " "
						+ myResultSet.getString("lastName")   
						+ "\n");
			}

			myConnection.close();

		} catch (SQLException e) {
			System.out.println("Contacts could not be saved. See error below");
			e.printStackTrace();
		}
	}



	public void loadDataFromSQL(){

		try {

			Statement myStatement = myConnection.createStatement();

			ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM contacts");


			while (myResultSet.next()) { // As long as there are contacts to retrieve in the db, retrieve them and store them in memory using ArrayList

				//check the column 'resStatus' in database to see the contact's residency status
				if(myResultSet.getString("resStatus").contains("Local")) { //if local

					contacts.add(new LocalContacts(
							myResultSet.getString("firstName"), 
							myResultSet.getString("lastName"), 
							myResultSet.getString("phoneNumber"),
							myResultSet.getString("address"),
							myResultSet.getString("resStatus")
							));
				}else if (myResultSet.getString("resStatus").contains("International")){ //or if it is international

					contacts.add(new InternationalContacts(
							myResultSet.getString("firstName"), 
							myResultSet.getString("lastName"), 
							myResultSet.getString("phoneNumber"),
							myResultSet.getString("address"),
							myResultSet.getString("resStatus")
							));
					
				}
			}   

//			String sqlReset = "DELETE FROM contacts;";
//			Statement resetStatement = myConnection.createStatement();
//
//			resetStatement.execute(sqlReset);

		} catch (SQLException e) {
			System.out.println("Contacts could not be loaded. See error below");
			e.printStackTrace();
		}
	}


}

