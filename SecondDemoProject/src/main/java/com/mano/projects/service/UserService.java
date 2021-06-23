package com.mano.projects.service;

import java.util.List;

import com.mano.projects.model.User;

public interface UserService {
	List<User> getAllUsers();
	
	User createUser(User user);
	
	User updateUser(int id, User user);
	
	void deleteUser(int id);
	
	User getUserById(int id);
	
}
