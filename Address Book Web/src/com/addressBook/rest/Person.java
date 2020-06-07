package com.addressBook.rest;

import com.addressBook.rest.Address;

class Person extends Address{
	  private String firstName;
	  private String lastName;
	  private String phoneNumber;
	  private String status;


	  // Initializer
	  public Person() {
	    firstName = "Jane";
	    lastName = "Doe";
	    phoneNumber = "xxx-xxx-xxxx";
	    status = "No Status";
	  }

	  public Person(String firstName, String lastName, String phoneNumber) {
		  this.firstName = firstName;
		  this.lastName = lastName;
		  this.phoneNumber = phoneNumber;
		  
	  }
	  // setter for First Name
	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }
	  // getter for First Name
	  public String getFirstName() {
	    return firstName;
	  }

	  // setter for Last Name
	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }
	  // getter for Last Name
	  public String getLastName() {
	    return lastName;
	  }

	  // setter for Phone Number
	  public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  // getter for Phone Number
	  public String getPhoneNumber() {
	    return phoneNumber;
	  }
	  
	  // default status setter
	  public void setStatus(String status) {
		  this.status = status;
		  status = "No Status";
	  }
	  
	  // default status getter
	  public String getStatus() {
		  return status;
	  }

	  // Overriding the prewritten toString function for our own use
	  @Override
	  public String toString() {
	    return (this.firstName + " " + this.lastName);
	  }

	}