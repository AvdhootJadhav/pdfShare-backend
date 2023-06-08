package com.avdhoot.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avdhoot.springboot.config.PersonUserDetails;
import com.avdhoot.springboot.config.PersonUserDetailsService;
import com.avdhoot.springboot.entity.Person;
import com.avdhoot.springboot.model.LoginRequest;
import com.avdhoot.springboot.model.LoginResponse;
import com.avdhoot.springboot.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String homePage() {
		return "Hello user";
	}
	
	@PostMapping("/new")
	public String addNewPerson(@RequestBody Person person) {
		return personService.addNewPerson(person);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Person> getPersons(){
		return personService.getPersons();
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		return personService.login(loginRequest);
	}
	
}
