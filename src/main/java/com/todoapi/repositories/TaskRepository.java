package com.todoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapi.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
