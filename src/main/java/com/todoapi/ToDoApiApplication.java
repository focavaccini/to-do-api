package com.todoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoApiApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(ToDoApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}

}
