package com.addressBook.rest;

import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/GetContact")
public class GetContact {

	@Path("{lastName}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String convertCtoFfromInput(@PathParam("lastName") String lastName) {

		AddressBookDB db = new AddressBookDB(); // init database
		db.loadDataFromSQL();


		String result;
		int i = 0;
		db.sortAddressBook(db.contacts); // sorting for the binarySearch to work
		int searchResult = db.binarySearch(db.contacts, lastName);// look for that lastName in your contact list

		if (searchResult == -1) {
			result = "<h2><div><center>Error 404: Contact not found!</center></div></h2>";
		}else {
			result = "<h1><div><center>Contact you searched for</center></div></h1>";
			result += "<br><div style='background-color:lightblue'>"
					+ "<h4><u>Name:</u>\t" + db.contacts.get(searchResult).getFirstName() + " " 
					+ db.contacts.get(searchResult).getLastName() + "<br>"
					+ "<u>Phone Number:</u>\t" + db.contacts.get(searchResult).getPhoneNumber() + "<br>"
					+ "<u>Address:</u>\t" + db.contacts.get(searchResult).getAddress() + "<br>"
					+ "<u>Residency:</u>\t" + db.contacts.get(searchResult).getStatus() + "<br>"
					+ "</h4></div>";
		}

		return result;
	}
	
}
