package com.mano.practice;

public class Student {
	private String userId;
	private String userName;
	
	Student(String id,String name){
		this.userId = id;
		this.userName = name;
	}
	
	public String getId() {
		return userId;
	}
	
	public String getName() {
		return userName;
	}
}
