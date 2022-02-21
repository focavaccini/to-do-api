package com.todoapi.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapi.entities.Task;
import com.todoapi.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;
	
	public List<Task> findAll(){
		return repository.findAll();
	}
	
	public Task findById(Long id) {
		Optional<Task> obj =  repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Task.class.getName(), null));
	}
	
	public Task insert(Task obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}
	
		public Task update(Task obj) {
		Task newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);	
		
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
	private void updateData(Task newObj, Task obj) {
		newObj.setName(obj.getName());
		newObj.setPriority(obj.getPriority());
		newObj.setStatus(obj.getStatus());
	}

}
