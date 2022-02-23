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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapi.entities.User;
import com.todoapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users endpoints")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Operation(summary = "Find all users")
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Find a specific user by your ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<User> find(@PathVariable Long id) {
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Find a specific user by your email")
	@GetMapping(value="/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Insert new user")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody User user) {
		User obj = service.insert(user);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@Operation(summary = "Update user by your ID")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody User user, @PathVariable Long id){
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Delete user by your ID")
	@DeleteMapping(value="/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
