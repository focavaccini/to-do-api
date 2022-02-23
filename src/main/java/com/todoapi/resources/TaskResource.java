package com.todoapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks endpoints")
public class TaskResource {
	
	@Autowired
	private TaskService service;
	
	@Operation(summary = "Find all tasks")
	@GetMapping
	public ResponseEntity<List<Task>> findAll(){
		List<Task> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Find a specific task by your ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Task> find(@PathVariable Long id) {
		Task obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Insert new task")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Task task) {
		Task obj = service.insert(task);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@Operation(summary = "Update task by your ID")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Task task, @PathVariable Long id){
		task.setId(id);
		task = service.update(task);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Mark task by your ID")
	@PutMapping(value="/markTask/{id}")
	public ResponseEntity<Void> markTask(@RequestBody Task task, @PathVariable Long id){
		task.setId(id);
		task = service.updateTaskFinish(task);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Delete task by your ID")
	@DeleteMapping(value="/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Task task = service.findById(id);
		service.delete(id, task);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Paging all tasks")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Task>> listTasks(
			@RequestParam(value = "page",defaultValue = "0") Integer pages, 
			@RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy",defaultValue = "priority")String orderBy, 
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		Page<Task> list  = service.listTasks(pages, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Find all pending tasks by user")
	@GetMapping(value = "/list/{status}")
	public ResponseEntity<List<Task>> filter(@Param("status") Integer status){
		List<Task> listaList = service.filter(status);
		return ResponseEntity.ok().body(listaList);
	}

}
