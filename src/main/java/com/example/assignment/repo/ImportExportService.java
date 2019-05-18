package com.example.assignment.repo;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.assignment.model.Contact;
import com.example.assignment.model.Device;
import com.example.assignment.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ImportExportService {
	
	@Autowired
	UserRepo userRepo;
	
    @Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	 UserRepoImpl    userRepoImpl;

	
	public String exportToFile(String userName, String deviceName){
		ObjectMapper mapper= new ObjectMapper();
		
		Device user= userRepoImpl.listContactByUserAndDevice(userName, deviceName);
		try{
			mapper.writeValue(new File("C:\\Temp\\Contacts.json"), user);
			mapper.writeValueAsString(user);
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return "File Exported at C:\\Temp\\Contacts.json" ;
		
	}
	
	
	
	public Device importFromFile(String fileName, String userName,String deviceName){
		
ObjectMapper mapper= new ObjectMapper();
		try{
		Device oldDevice= userRepoImpl.listContactByUserAndDevice(userName, deviceName);
		Device newDevice= mapper.readValue(new File("C:\\Temp\\" +fileName), Device.class);
		
		List<Contact> updatedContact = new ArrayList<>();
		
		updatedContact.addAll(oldDevice.getContact());
		updatedContact.addAll(newDevice.getContact());
		
		List<Contact> unique = updatedContact.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparing(Contact::getName))), ArrayList::new));
		Query select = Query.query(new Criteria().andOperator(Criteria.where("name").is(userName),
				Criteria.where("device.name").is(deviceName)));
		Update update = new Update();
		update.set("device.$.contact", unique);
		mongoTemplate.findAndModify(select, update, User.class);
	}
	catch(IOException e){
		e.printStackTrace();
	}
	return userRepoImpl.listContactByUserAndDevice(userName, deviceName);
}

}

