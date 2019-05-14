package com.example.assignment.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class Device {

	private String name;
	private String IP;

	@Field
	List<Contact> contact;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	Device(){
		super();
	}

	public Device(String name, String iP, List<Contact> contact) {
		super();
		this.name = name;
		IP = iP;
		this.contact = contact;
	}
	


}
