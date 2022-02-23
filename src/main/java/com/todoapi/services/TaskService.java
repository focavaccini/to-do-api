package com.todoapi.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.todoapi.entities.Task;
import com.todoapi.entities.User;
import com.todoapi.repositories.TaskRepository;
import com.todoapi.repositories.UserRepository;
import com.todoapi.security.JWTAuthenticationFilter;
import com.todoapi.security.JWTUtil;
import com.todoapi.security.UserSS;
import com.todoapi.services.authentication.UserServiceAuthentication;
import com.todoapi.services.exceptions.AuthorizationException;

@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Task> findAll(){
		return repository.findAll();
	}
	
	public Task findById(Long id) {
		Optional<Task> obj =  repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Task.class.getName(), null));
	}
	
	public Task insert(Task obj) {
		obj.setId(null);
		obj.setTokenUser(JWTAuthenticationFilter.token);
		obj.setUser(userService.findByEmail(jwtUtil.getUsername(JWTAuthenticationFilter.token)));
		obj = repository.save(obj);
		return obj;
	}
	
	public Task update(Task obj) {
		String email = obj.getUser().getEmail();
		if(validateResponsibleForTheTask(email) == false) {
			throw new AuthorizationException("Access denied");
		}
			Task newObj = findById(obj.getId());
			updateData(newObj, obj);
			return repository.save(newObj);	
		
	}

	public void delete(long id, Task task) {
		String email = task.getUser().getEmail();
		if(validateResponsibleForTheTask(email) == false) {
			throw new AuthorizationException("Access denied");
		}
		repository.deleteById(id);
	}
	
	private void updateData(Task newObj, Task obj) {
		newObj.setName(obj.getName());
		newObj.setPriority(obj.getPriority());
		newObj.setStatus(obj.getStatus());
	}

	public Task updateTaskFinish(Task obj) {
		String email = obj.getUser().getEmail();
		if(validateResponsibleForTheTask(email) == false) {
			throw new AuthorizationException("Access denied");
		}
			Task newObj = findById(obj.getId());
			updateTaskState(newObj, obj);
			return repository.save(newObj);
	}
	
	private void updateTaskState(Task newObj, Task obj) {
		newObj.setStatus(obj.getStatus());
	}
	
	public Page<Task> listTasks(Integer pages, Integer linesPerPage, String orderBy, String direction){
		UserSS userSS = UserServiceAuthentication.authenticated();
		if(userSS == null) {
			throw new AuthorizationException("Access denied");
		}
		
		User user = userRepository.findById(userSS.getId()).get();
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findByUser(user, pageRequest);
	}
	
	
	public Boolean validateResponsibleForTheTask(String email) {
		if(email.equals(jwtUtil.getUsername(JWTAuthenticationFilter.token))) {
			return true;
		}
		return false;
	}
}
