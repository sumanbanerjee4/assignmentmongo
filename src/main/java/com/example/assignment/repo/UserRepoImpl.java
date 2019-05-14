package com.example.assignment.repo;

import java.util.ArrayList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.assignment.model.Contact;
import com.example.assignment.model.Device;
import com.example.assignment.model.User;

public class UserRepoImpl implements UserRepoCustom {

	@Autowired
	UserRepo userRepo;

	private final MongoTemplate mongoTemplate;

	@Autowired
	public UserRepoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public Device listContactByUserAndDevice(String userName, String deviceName) {

		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		list.add(Aggregation.match(Criteria.where("name").is(userName)));
		list.add(Aggregation.unwind("device"));
		list.add(Aggregation.match(Criteria.where("device.name").is(deviceName)));
		list.add(Aggregation.project("device.contact"));
		TypedAggregation<Device> agg = Aggregation.newAggregation(Device.class, list);

		return mongoTemplate.aggregate(agg, User.class, Device.class).getUniqueMappedResult();
	}

	public List<Device> findListOfDeviceByUser(String userName) {

		List<AggregationOperation> list = new ArrayList<>();
		list.add(Aggregation.match(Criteria.where("name").is(userName)));
		list.add(Aggregation.unwind("device"));
		list.add(Aggregation.project("device.name", "device.IP", "device.contact"));
		TypedAggregation<Device> agg = Aggregation.newAggregation(Device.class, list);

		return mongoTemplate.aggregate(agg, User.class, Device.class).getMappedResults();
	}

	public Device addContactByDeviceName(String userName, String deviceName, Contact c) {
		Device device = listContactByUserAndDevice(userName, deviceName);
		List<Contact> oldContacts = device.getContact();
		for(Contact oc : oldContacts) {
			
			if(oc.getName().equals(c.getName())) {
				oc.setEmail(c.getEmail());
				oc.setPhoneNumber(c.getPhoneNumber());
			}
		}

		oldContacts.add(c);
		List<Contact> unique = oldContacts.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparing(Contact::getName))), ArrayList::new));
		Query select = Query.query(new Criteria().andOperator(Criteria.where("name").is("userName"),
				Criteria.where("device.name").is(deviceName)));
		Update update = new Update();
		update.set("device.$.contact", unique);
		mongoTemplate.findAndModify(select, update, User.class);
		return listContactByUserAndDevice(userName , deviceName);

		/*
		 * mongoTemplate.updateMulti( query, Update.update( "device", new
		 * Device(device.getName(), device.getIP(), oldContacts)), User.class);
		 */
	}

	public Device syncContactByDeviceName(String userName, String deviceName) {
		List<Device> devices = findListOfDeviceByUser(userName);
		List<Contact> c = new ArrayList<>();
		for (Device d : devices) {
			c.addAll(d.getContact());
		}

		List<Contact> unique = c.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparing(Contact::getName))), ArrayList::new));
		Query select = Query.query(new Criteria().andOperator(Criteria.where("name").is("userName"),
				Criteria.where("device.name").is(deviceName)));
		Update update = new Update();
		update.set("device.$.contact", unique);
		mongoTemplate.findAndModify(select, update, User.class);
		return listContactByUserAndDevice(userName, deviceName);

	}
}