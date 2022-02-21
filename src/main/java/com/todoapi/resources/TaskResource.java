package com.todoapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapi.entities.Task;
import com.todoapi.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskResource {
	
	@Autowired
	private TaskService service;
	
	
	@GetMapping
	public ResponseEntity<List<Task>> findAll(){
		List<Task> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Task> find(@PathVariable Long id) {
		Task obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Task user) {
		Task obj = service.insert(user);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Task user, @PathVariable Long id){
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
