package com.avdhoot.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avdhoot.springboot.entity.Person;
import com.avdhoot.springboot.model.LoginRequest;
import com.avdhoot.springboot.model.LoginResponse;
import com.avdhoot.springboot.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PersonRepository personRepository;

	public String addNewPerson(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		personRepository.save(person);
		return "User added";
	}

	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	public LoginResponse login(LoginRequest loginRequest) {
		Person person = personRepository.findByName(loginRequest.getName()).get();
		
		if(person == null) {
			throw new UsernameNotFoundException("User not Found");
		}
		else if(passwordEncoder.matches(loginRequest.getPassword(), person.getPassword())) {
			LoginResponse response = new LoginResponse(person.getId(), person.getName(), person.getEmail());
			System.out.println(response);
			return response;
		}
		
		return null;
	}
	
	
}
