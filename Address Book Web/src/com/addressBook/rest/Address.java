package com.addressBook.rest;

public class Address extends AddressBook{

    String address;

	Address(){
		address = "Default Address Goes Here";
	};

	// setter for Address
	public void setAddress(String address){
		this.address = address;
	}
	
	// getter for Address
	public String getAddress(){
		return this.address;
	}

}
