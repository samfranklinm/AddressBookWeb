package com.addressBook.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class AddressBook{
	ArrayList<Person> contacts = new ArrayList<Person>();
	int index = 0;

	//Function to add contact to the Address Book
	public void addContact() throws IOException {

		Scanner input = new Scanner(System.in);
		System.out.print("Is the contact being added a local contact(type L) or an internation contact (type I)? ");
		String localOrInt = input.nextLine();


		// If contact is locally located - create a local contact
		switch (localOrInt) {
		case "L":  
			Person person = new LocalContacts();

			// set firstName
			System.out.print("Enter contact's first name: ");
			person.setFirstName(input.nextLine());

			// set lastName
			System.out.print("Enter contact's last name: ");
			person.setLastName(input.nextLine());

			// set phoneNumber
			System.out.print("Enter contact's phone number: ");
			person.setPhoneNumber(input.nextLine());

			// set Address
			System.out.println("Enter contact's address (one field at a time - starting with street number, street name, city, state, zipcode): ");
			person.setAddress(input.nextLine());

			// add contact to the list
			contacts.add(person);
			System.out.println("Contact added " + person.getFirstName() + " to the list!" );
			System.out.println();
			break;

		case "I":  // If contact is internationally located - create an international contact
			Person interPerson = new InternationalContacts();
			interPerson.getStatus();

			// set firstName
			System.out.print("Enter contact's first name: ");
			interPerson.setFirstName(input.nextLine());

			// set lastName
			System.out.print("Enter contact's last name: ");
			interPerson.setLastName(input.nextLine());

			// set phoneNumber
			System.out.print("Enter contact's phone number: ");
			interPerson.setPhoneNumber(input.nextLine());

			// set Address
			System.out.println("Enter contact's address (one field at a time - starting with street number, street name, city, state, zipcode): ");
			interPerson.setAddress(input.nextLine());

			// add contact to the list
			contacts.add(interPerson);
			System.out.println("Contact added " + interPerson.getFirstName() + " to the list!" );
			System.out.println();
			break;

		default: 
			System.out.println("Not a valid option, try again");
			break;
		}

	}

	// Function to display the contacts list in Address Book
		public void printAddressBook(){
			sortAddressBook(contacts); // sort the contact list by first name

			// Print out contacts' information on console
			System.out.println("---------ADDRESS BOOK----------");
			
			for(int i = 0; i < contacts.size(); i++) {
			System.out.println( contacts.get(i).toString() + " " 
					+ " [" + contacts.get(i).getStatus() + "] "
					+ "\n" 
					+ contacts.get(i).getAddress()
					+ "\n");
			}
			System.out.println("--------------------------------");
		}

	// Function to search for a specific contact
	public void searchContact() {

		Scanner input = new Scanner(System.in);

		System.out.println("What's the first name of the contact you are trying to look up?: ");
		String firstName = input.nextLine();

		sortAddressBook(contacts); // sorting for the binarySearch to work
		int searchResult = binarySearch(contacts, firstName);// look for that firstName in your contact list

		if (searchResult == -1) {
			System.out.println("The person you are trying to update is currently not in the Address Book.\n");
		}else {
			System.out.print("Contact's Name: " + contacts.get(searchResult).getFirstName() + " ");
			System.out.print(contacts.get(searchResult).getLastName() + " \n");
			System.out.println("Contact's Phone Number: " + contacts.get(searchResult).getPhoneNumber() + " ");
			System.out.print("Contact Address: " + contacts.get(searchResult).getAddress().toString());
			System.out.println();
		}

	}

	// Function to update contact
	public ArrayList<Person> updateContact() {
		String lastName;
		String firstName;
		Scanner input = new Scanner(System.in);
		System.out.println("Who would you like to update (enter first name only): ");
		firstName = input.nextLine();

		sortAddressBook(contacts); // sorting for the binarySearch to work
		int searchResult = binarySearch(contacts, firstName); // look for that firstName in your contact list

		if (searchResult == -1) {
			System.out.println("The person you are trying to update is currently not in the Address Book.\n");
		} else {
			System.out.println("\nEnter the updated information below. . .\n");
			System.out.print("Enter contact's first name: ");
			contacts.get(searchResult).setFirstName(input.nextLine()); // update firstName

			System.out.print("Enter contact's last name: ");
			contacts.get(searchResult).setLastName(input.nextLine()); // set lastName

			System.out.print("Enter contact's phone number: ");
			contacts.get(searchResult).setPhoneNumber(input.nextLine()); // set phoneNumber)

			System.out.println("Enter contact's address (one field at a time - starting with street number, street name, city, state, zipcode): ");
			contacts.get(searchResult).setAddress(input.nextLine()); // set address
			System.out.println();

			firstName = contacts.get(searchResult).getFirstName();
			lastName = contacts.get(searchResult).getLastName();

			System.out.println("Updating " + firstName + " " + lastName + ". . .");

			//contacts = (ArrayList<Person>) contacts.clone();
			//System.arraycopy(contacts, 0, contacts, 0, searchResult);
		}
		return contacts;

	}

	// Function to delete contact
	public ArrayList<Person> deleteContact() {
		String firstName;
		Scanner input = new Scanner(System.in);
		System.out.println("Who would you like to delete (enter first name only): ");
		firstName = input.nextLine();

		sortAddressBook(contacts); // sorting for the binarySearch to work

		int searchResult = binarySearch(contacts,firstName);// look for that firstName in your contact list

		if (searchResult == -1) {
			System.out.println("The person you are trying to delete is currently not in the Address Book.\n");
		} else {
			System.out.println("Deleting " + firstName + ". . .");
			// Using library to copy array but to itself and deleting the specified contact
			// at the specified index (searchResult)
			contacts.remove(searchResult);
		}
		return contacts;
	}

	// Function to sort Address Book alphabetically
	public void sortAddressBook(ArrayList<Person> contactList) {

		contactList = contacts;
		// Using a simple Bubble Sort function to sort out the contacts' first names in
		// the Address Book alphabetically
		for (int i = 0; i < contacts.size(); i++) {
			for (int j = 0; j < contacts.size() - i - 1; j++) {
				// if the contacts's firstName's first letter comes after the next contact's
				// firstName's first letter in the alphabet table, then swap contacts.
				if (contacts.get(j).getFirstName().charAt(0) > contacts.get(j+1).getFirstName().charAt(0)) {
					Person temp = contacts.get(j);
					contacts.set(j, contacts.get(j+1));
					contacts.set(j+1, temp);
				}
			}
		}
	}

	// Function to search for the contact to delete and update contact
	public int binarySearch(ArrayList<Person> contactList, String lastName) {

		contactList = contacts;

		int first = 0, last = contacts.size() - 1;

		while (first <= last) {
			int middle = (first + last) / 2;
			int itsHere = lastName.compareTo((contacts.get(middle).getLastName()));

			if (itsHere == 0) // if the first index we look at is where the key-index we're looking for is,
				// then,
				return middle; // return that index
			if (itsHere > 0) // if the key-index lies somewhere beyond the first index, then,
				first = middle + 1; // ignore the rest of the indices before the middle + 1 index
			else // else it's safe to assume that the key-index is somewhere before the middle
				last = middle - 1; // ignore the rest of the indices after mid - 1

			middle = (first + last) / 2; // update the mid point
		}
		return -1;
	}

	// Function to exit the program
	public void closeAddressBook(){
		AddressBookDB db = new AddressBookDB();
		db.contacts = contacts;
		try { 
			db.saveDataIntoSQL();
			System.out.println("Address book closed.");
			System.exit(0);
		}catch(Exception e) {
			System.out.println("Address book did not close properly..");
			e.printStackTrace();
		}
	}


}