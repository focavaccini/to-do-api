package com.todoapi.services.authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import com.todoapi.security.UserSS;

public class UserServiceAuthentication {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
	}
}
