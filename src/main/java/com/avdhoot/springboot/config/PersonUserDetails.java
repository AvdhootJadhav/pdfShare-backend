package com.avdhoot.springboot.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.avdhoot.springboot.entity.Person;

public class PersonUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public PersonUserDetails(Person person) {
		this.name = person.getName();
		this.password = person.getPassword();
		try {
			this.authorities = Arrays.stream(person.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			System.out.println(authorities);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
