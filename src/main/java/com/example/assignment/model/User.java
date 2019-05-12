package com.example.assignment.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreType;


@Document
@JsonIgnoreType()
public class User {
	
	String name;
	@Id
	String id;
	
	User(){};
	
	public List<Device> device;
	
	
	
	public User(String name, List<Device> device) {
		super();
		this.name = name;
		this.device = device;
	}
	
	public String getUser() {
		return name;
	}
	public void setUser(String user) {
		name = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
