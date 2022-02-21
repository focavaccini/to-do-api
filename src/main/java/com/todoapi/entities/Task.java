package com.todoapi.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.todoapi.entities.enums.PriorityTask;
import com.todoapi.entities.enums.StatusTask;

@Entity
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer priority;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(Long id, String name, PriorityTask priorityTask, StatusTask statusTask) {
		super();
		this.id = id;
		this.name = name;
		this.priority = (priorityTask == null) ? null : priorityTask.getCode();
		this.status = (statusTask == null) ? null : statusTask.getCode();
		this.user = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}
	
}
