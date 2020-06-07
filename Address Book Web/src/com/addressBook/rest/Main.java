//package com.addressBook.rest;
//import java.util.Scanner;
//
//class Main extends AddressBookDB{
//
//	public static int choice;
//	public static boolean getContinue() {
//		System.out.print("\nContinue y/n? ");
//		Scanner scan = new Scanner(System.in);
//		// this is a boolean check
//		return (scan.next().charAt(0) == 'y');
//	}
//
//	public static int menu() {
//
//		System.out.println("\nAddress Book");
//		System.out.println("-------------");
//		System.out.println("1. Add a new contact");
//		System.out.println("2. Update an existing contact");
//		System.out.println("3. Delete a contact");
//		System.out.println("4. List all added contacts in sorted order");
//		System.out.println("5. Search for a given contact. Search can be done by first name, last name or phone number. Return all the details upon finding a match.");
//		System.out.println("6. Quit");
//
//		Scanner scan = new Scanner(System.in);
//		return scan.nextInt();
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		AddressBookDB db = new AddressBookDB();
//
//		db.loadDataFromSQL(); 
//
//		do {
//			choice = menu();
//			switch (choice) {
//			case 1:
//				db.addContact();
//				break;
//			case 2:
//				db.updateContact();
//				break;
//			case 3:
//				db.deleteContact();
//				break;
//			case 4:
//				db.printAddressBook();
//				break;
//			case 5:
//				db.searchContact();
//				break;
//			case 6:
//				db.closeAddressBook();
//				break;
//			}
//
//		} while (getContinue());
//
//	}
//}
