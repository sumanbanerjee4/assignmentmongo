package com.example.assignment.configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.assignment.model.Contacts;
import com.example.assignment.model.Device;
import com.example.assignment.repo.ContactRepo;

@EnableMongoRepositories(basePackageClasses = ContactRepo.class)
@Configuration
public class MongoDBConfig {

	
	

	@Bean
	CommandLineRunner commandLineRunner(ContactRepo contactrepo) {
		return strings -> {
			contactrepo.deleteAll();
	//	contactrepo.save(new User("suman",hArrays.asList(new Device("Android","192.168.146.78"),new Device("Lenovo-pc","192.178.146.78"))));
//			contactrepo.save(new Contacts("Sam",  2000, "ab.game@gmail.com",Arrays.asList(new Device("Android","192.168.146.78"),new Device("Iphone","192.168.146.77"))));
		};
	}
	

}


