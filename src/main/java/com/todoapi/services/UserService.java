package com.todoapi.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapi.entities.User;
import com.todoapi.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj =  repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + User.class.getName(), null));
	}
	
	public User insert(User obj) {
		obj.setId(null);
		obj.setPassword(bCrypt.encode(obj.getPassword()));
		obj = repository.save(obj);
		return obj;
	}
	
		public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);	
		
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public User findByEmail(String email) {
	
		User user = repository.findByEmail(email);
		return user;
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
	}

}
