package com.example.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.List;

import javax.validation.Valid;


import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class Contacts {
	
	String name;
	
	
	int phoneNumber;
	
	
	String email;

	
	public Contacts(@Valid String name, @Valid int phoneNumber, @Valid String email) {

		
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Contacts() {}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	


}
