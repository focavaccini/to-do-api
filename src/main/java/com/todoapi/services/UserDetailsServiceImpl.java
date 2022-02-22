package com.todoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.todoapi.entities.User;
import com.todoapi.repositories.UserRepository;
import com.todoapi.security.UserSS;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User with email: " + username + ", not found");
		}
		
		return new UserSS(user.getId(), user.getEmail(), user.getPassword());
	}

}
