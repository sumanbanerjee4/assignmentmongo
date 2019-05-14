package com.example.assignment.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreType;


@Document
@JsonIgnoreType()
public class User {
	
	@Valid
	@Indexed(unique=true)
	private String name;
	@Id
	String id;
	@Field
	@Indexed(unique=true)
	private List<Device> device;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	User(){}

	public User(@Valid String name, List<Device> device) {
		super();
		this.name = name;
		this.device = device;
	};
	
	
	
	

}
