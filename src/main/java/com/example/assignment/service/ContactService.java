//package com.example.assignment.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.assignment.model.Contact;
//import com.example.assignment.repo.ContactRepo;
//
//@Service
//public class ContactService {
//	
//	@Autowired
//	ContactRepo contactsRepository;
//	
//	public Contact createContacts(Contact contacts){
//		Contact Contacts1 = contactsRepository.save(contacts);
//		return Contacts1;
//		
//	}
//
//	public List<Contact> getAllContacts() {
//		List<Contact> ContactsList = contactsRepository.findAll();
//		return ContactsList;
//	}
//
//	public String updateContacts(@Valid Contact contact) {
//		contactsRepository.save(contact);
//		return "Contacts Sucessfully updated";
//	}
//
//	public String deleteContacts(String name) {
//		contactsRepository.deleteById(name);
//		return "Contacts Sucessfully deleted";
//	}
//	
//	public String deleteAll() {
//		contactsRepository.deleteAll();
//		return "Contacts Sucessfully deleted";
//	}
//	
//	public Optional<Contact> getContactByName(String name) {
//		Optional<Contact>contact= contactsRepository.findByName(name);
//		return contact;
//	}
//	
//	public Contact getContactByDevice(String device,String name) {
//		Contact contact= contactsRepository.findByDevice(name,device);
//		return contact;
//	}
//
//}