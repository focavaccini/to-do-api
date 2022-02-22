package com.todoapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Void> insert(@RequestBody Task task) {
		Task obj = service.insert(task);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Task task, @PathVariable Long id){
		task.setId(id);
		task = service.update(task);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/markTask/{id}")
	public ResponseEntity<Void> markTask(@RequestBody Task task, @PathVariable Long id){
		task.setId(id);
		task = service.updateTaskFinish(task);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Task task = service.findById(id);
		service.delete(id, task);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Task>> listTasks(
			@RequestParam(value = "page",defaultValue = "0") Integer pages, 
			@RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy",defaultValue = "priority")String orderBy, 
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		Page<Task> list  = service.listTasks(pages, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
