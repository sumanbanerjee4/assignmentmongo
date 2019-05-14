package com.example.assignment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Contact;
import com.example.assignment.model.Device;
import com.example.assignment.model.User;
import com.example.assignment.repo.UserRepo;
import com.example.assignment.repo.UserRepoImpl;


@RestController
@RequestMapping("/contacts")
public class Controller {

	/*
	 * @Autowired ContactService contactService;
	 * 
	 * @Autowired ContactRepo contactsRepository;
	 * 
	 * @GetMapping("/all") public List<Contacts> getAll() { return
	 * contactService.getAllContacts(); }
	 * 
	 * @PostMapping("/createContact") public Contacts createUserDetails(@RequestBody
	 * Contacts contacts) { return contactService.createContacts(contacts); }
	 * 
	 * @GetMapping("/user/{name}") public Optional<Contacts>
	 * getContactByName(@PathVariable("name") String name) { return
	 * contactService.getContactByName(name); }
	 * 
	 * @Cacheable
	 * 
	 * @GetMapping("/{name}/{device}") public Contacts
	 * getContactByDevice(@PathVariable("name") String name,@PathVariable String
	 * device) { return contactsRepository.findByDevice(name,device); }
	 * 
	 * @CacheEvict(allEntries=true)
	 * 
	 * @GetMapping("syncContacts/{name}/{device}") public Contacts
	 * getContactByDeviceSync(@PathVariable("name") String name,@PathVariable String
	 * device) { return contactsRepository.findByDevice(name,device); }
	 * 
	 * @PutMapping("updateByDeviceName/{name}/{device}") public String
	 * modifyByDeviceName(@PathVariable("name") String name,@PathVariable("device")
	 * String device, @Valid @RequestBody Contacts contact) { Contacts temp =
	 * contactsRepository.findByDevice(name,device); if(temp != null) {
	 * temp.setEmail(contact.getEmail()); temp.setName(contact.getName());
	 * temp.setPhoneNumber(contact.getPhoneNumber()); contactsRepository.save(temp);
	 * return temp.toString(); } return "Invalid Request"; }
	 * 
	 * 
	 * @DeleteMapping("/delete/{name}") public String
	 * deleteUserDetails(@PathVariable String name) { return
	 * contactService.deleteContacts(name); }
	 * 
	 * @DeleteMapping("/deleteAll") public String deleteAll() { return
	 * contactService.deleteAll(); }
	 */

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserRepoImpl userRepoImpl;
	
	/*@Autowired
	ImportExportService importExport;*/

	@PostMapping("/createuser")
	public User addUser(@RequestBody User user) {

		return userRepo.save(user);
	}

	@GetMapping("/allUser")
	public List<User> getUsers() {
		List<User> u = userRepo.findAll();
		return u;
	}
	
	

	@GetMapping("/allContactByDevice/{userName}/{deviceName}")
	public Device getContactByName(@PathVariable("userName") String userName,
			@PathVariable("deviceName") String deviceName) {

		return userRepoImpl.listContactByUserAndDevice(userName, deviceName);
	}

	@PutMapping("addContactsByDeviceName/{name}/{device}")
	public String addContactByDeviceName(@PathVariable("name") String name, @PathVariable("device") String device,
			@Valid @RequestBody Contact contact) {

		userRepoImpl.addContactByDeviceName(name, device, contact);
		return "Success";

	}

	@GetMapping("findListOfDevice/{userName}")
	public List<Device> listOfDevices(@PathVariable("userName") String userName) {

		return userRepoImpl.findListOfDeviceByUser(userName);
	}

	@PutMapping("syncContactByDeviceName/{name}/{device}")
	public Device syncContactByDeviceName(@PathVariable("name") String userName,
			@PathVariable("device") String deviceName) {

		return userRepoImpl.syncContactByDeviceName(userName, deviceName);

	}
}
