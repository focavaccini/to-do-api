package com.todoapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todoapi.entities.Task;
import com.todoapi.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("SELECT obj FROM Task obj WHERE obj.status = 0")
	Page<Task> findByUser(User user, Pageable pageRequest);
	
	@Query("SELECT obj FROM Task obj WHERE obj.status = 0 AND obj.user.email = email ORDER BY obj.priority")
	List<Task> filterByStatus(Integer status, String email);
}
