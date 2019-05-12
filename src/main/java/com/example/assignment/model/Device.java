package com.example.assignment.model;

import java.util.List;

public class Device {

public Device(String name, String iP, List<Contacts> contacts) {
		super();
		this.name = name;
		IP = iP;
		this.contacts = contacts;
	}
private String name;
private String IP;

public List<Contacts> contacts;

public String getIP() {
	return IP;
}
public void setIP(String iP) {
	IP = iP;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
Device(){}


}
