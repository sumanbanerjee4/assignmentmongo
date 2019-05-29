package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.assignment.model.Contact;
import com.example.assignment.model.Device;
import com.example.assignment.model.User;
import com.example.assignment.repo.UserRepo;
import com.example.assignment.repo.UserRepoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserRepoImpl userRepoImpl;
	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	public void listContactByUserAndDevice() {

		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		list.add(Aggregation.match(Criteria.where("name").is("suman")));
		list.add(Aggregation.unwind("device"));
		list.add(Aggregation.match(Criteria.where("device.name").is("Hp")));
		list.add(Aggregation.project("device.contact"));
		TypedAggregation<Device> agg=TypedAggregation.newAggregation(Device.class,list);

		Device device = mongoTemplate.aggregate(agg, User.class, Device.class).getUniqueMappedResult();
      
		List<Contact> s= device.getContact();
		
		for(Contact d: s) {
		
		System.out.println(d.getEmail());
	}

}}
