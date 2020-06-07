package com.addressBook.rest;


public class InternationalContacts extends Person implements LocalOrInternational{
	
	private String status;

	public InternationalContacts(){};

	public InternationalContacts(String firstName, String lastName, String phoneNumber,  String address ,String status) {
		super(firstName, lastName, phoneNumber);
		
		this.status = status;
		this.address = address;
	}

	@Override
	public String getStatus() {
		status = "International";
		return status;
	}

	@Override
	public void setStatus() {
		String status = null;
		this.status = status;
	}
}
