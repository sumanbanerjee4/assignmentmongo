package com.example.assignment.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.assignment.model.User;

public interface UserRepo extends MongoRepository<User, String>, UserRepoCustom{
	
	public Optional<User> findByName(String name); 

	
	
	@Query("{'$and':[{'name':?0},{'Device.name': ?1}]}")
	public  User findAllContactsByDevice(String userName,String deviceName);
}