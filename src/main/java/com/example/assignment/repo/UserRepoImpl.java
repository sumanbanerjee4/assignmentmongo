package com.example.assignment.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.example.assignment.model.Device;
import com.example.assignment.model.User;

public class UserRepoImpl implements UserRepoCustom{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	private final MongoTemplate mongoTemplate;
	
	
	public UserRepoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}
	
	public Device ListContactByUserAndDevice(String userName,String deviceName) {
		
		List <AggregationOperation> list =new ArrayList<AggregationOperation>();
		list.add(Aggregation.match(Criteria.where("name").is(userName)));
		list.add(Aggregation.unwind("device"));
		list.add(Aggregation.match(Criteria.where("device.name").is(deviceName)));
		list.add(Aggregation.project("device.contact"));
		TypedAggregation<Device> agg= Aggregation.newAggregation(Device.class, list);
		System.out.println(agg+ "." +list.toString());
		
		return mongoTemplate.aggregate(agg, User.class, Device.class).getUniqueMappedResult();
		
		
	}
	
	
	
	public List<Device> findListOfDeviceByUser(String userName){
		
		System.out.println(userName);
		return null;
		
		
		
		
		
	}

}
