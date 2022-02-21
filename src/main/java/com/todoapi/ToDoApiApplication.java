package com.todoapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.todoapi.entities.User;
import com.todoapi.repositories.UserRepository;

@SpringBootApplication
public class ToDoApiApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ToDoApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "nina@gmail.com", "Nina Brown", "123456");
		User user2 = new User(null,"leia@gmail.com", "Leia Red", "123456");
		User user3 = new User(null, "ana@gmail.com", "Ana Green","123456");
		User user4 = new User(null, "bet@gmail.com", "Bet Black", "123456");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		
	}

}
