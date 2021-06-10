package com.mano.pactice.models;

public class Hunter {
	int Id;
	String Name;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ Id : "+ Id + ", Name: " + Name+']';
	}
	
}
