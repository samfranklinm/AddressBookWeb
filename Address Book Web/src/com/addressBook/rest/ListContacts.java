package com.addressBook.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/ListAllContacts")
public class ListContacts {
	@GET
	@Produces(MediaType.TEXT_HTML)
	
	
	public String listAll() { 
		AddressBookDB db = new AddressBookDB(); // init database
	
		db.loadDataFromSQL();
				//<h4><div><center>Contacts:</center></div></h4>
		String screenResults = "";
		
		for(int i = 0; i < db.contacts.size(); i++) {
			screenResults += "<br><div style='background-color:lightblue'>"
			  + "<h4><u>Name:\t</u>" + db.contacts.get(i).getFirstName() + " " 
			  + db.contacts.get(i).getLastName()+ "<br>"
			  + "<u>Phone Number:\t</u>" + db.contacts.get(i).getPhoneNumber()+ "<br>"
			  + "<u>Address:\t</u>" + db.contacts.get(i).getAddress() + "<br>"
			  + "<u>Residency:\t</u>" + db.contacts.get(i).getStatus() + "<br>"
			  + "</h4></div>";
		}
		return screenResults;
	}
}