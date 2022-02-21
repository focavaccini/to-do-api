package com.todoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapi.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
