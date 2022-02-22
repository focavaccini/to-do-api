package com.todoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
