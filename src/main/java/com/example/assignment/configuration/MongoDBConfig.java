package com.example.assignment.configuration;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.assignment.model.Contact;
import com.example.assignment.model.Device;
import com.example.assignment.model.User;

import com.example.assignment.repo.UserRepo;

@EnableMongoRepositories(basePackageClasses = UserRepo.class)
@Configuration
public class MongoDBConfig {

	
	

	@Bean
	CommandLineRunner commandLineRunner(UserRepo userRepo) {
		return strings -> {
			userRepo.deleteAll();
		userRepo.save(new User("suman",Arrays.asList(new Device("Android","192.168.146.78",
			Arrays.asList
			(new Contact("sam",8999,"a@gmail.com"),
					(new Contact("sal",8996,"b@gmail.com")))))));
		//contactrepo.save(new Contacts("Sam",  2000, "ab.game@gmail.com",Arrays.asList(new Device("Android","192.168.146.78"),new Device("Iphone","192.168.146.77"))));
		};
	}
	

}


