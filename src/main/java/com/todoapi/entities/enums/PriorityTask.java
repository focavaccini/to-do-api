package com.todoapi.entities.enums;

public enum PriorityTask {
	
	HIGH(0, "HIGH"),
	MEDIUM(1, "MEDIUM"),
	LOW(2, "LOW");
	
	private int code;
	private String description;

	private PriorityTask(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	public static PriorityTask toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(PriorityTask x : PriorityTask.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id " + code);
	}
}
