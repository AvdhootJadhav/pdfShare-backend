package com.avdhoot.springboot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.avdhoot.springboot.entity.Person;
import com.avdhoot.springboot.repository.PersonRepository;

public class PersonUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> person = personRepository.findByName(username);
		
		System.out.println(person);
		
		return person.map(PersonUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
	}

}
