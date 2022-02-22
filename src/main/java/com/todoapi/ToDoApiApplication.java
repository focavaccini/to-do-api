package com.todoapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.todoapi.entities.Task;
import com.todoapi.entities.User;
import com.todoapi.entities.enums.PriorityTask;
import com.todoapi.entities.enums.StatusTask;
import com.todoapi.repositories.TaskRepository;
import com.todoapi.repositories.UserRepository;

@SpringBootApplication
public class ToDoApiApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
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
		
		Task task1 = new Task(null,"Task 1", PriorityTask.HIGH, StatusTask.PENDING);
		Task task2 = new Task(null,"Task 2", PriorityTask.MEDIUM, StatusTask.PENDING);
		Task task3 = new Task(null,"Task 3", PriorityTask.LOW, StatusTask.FINISHED);
		Task task4 = new Task(null,"Task 4", PriorityTask.HIGH, StatusTask.PENDING);
		Task task5 = new Task(null,"Task 5", PriorityTask.LOW, StatusTask.PENDING);
		Task task6 = new Task(null,"Task 6", PriorityTask.MEDIUM, StatusTask.PENDING);
		Task task7 = new Task(null,"Task 7", PriorityTask.HIGH, StatusTask.FINISHED);
		Task task8 = new Task(null,"Task 8", PriorityTask.MEDIUM, StatusTask.PENDING);
		Task task9 = new Task(null,"Task 9", PriorityTask.LOW, StatusTask.FINISHED);
		
		taskRepository.saveAll(Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8, task9));
		
		user1.getTasks().addAll(Arrays.asList(task1, task5, task9));
		user2.getTasks().addAll(Arrays.asList(task2, task6));
		user3.getTasks().addAll(Arrays.asList(task3, task7));
		user4.getTasks().addAll(Arrays.asList(task4, task8));
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
	}
}
