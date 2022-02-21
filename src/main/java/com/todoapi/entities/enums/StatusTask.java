package com.todoapi.entities.enums;

public enum StatusTask {
	
	PENDING(0, "PENDING"),
	FINISHED(1, "FINISHED");
	
	private int code;
	private String description;

	private StatusTask(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	public static StatusTask toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(StatusTask x : StatusTask.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id " + code);
	}
}
