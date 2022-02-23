package com.todoapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.todoapi.entities.Task;
import com.todoapi.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Transactional(readOnly=true)
	Page<Task> findByUser(User user, Pageable pageRequest);

}
