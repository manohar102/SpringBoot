package com.mano.projects.service;

import com.mano.projects.dto.UserDto;
import java.util.List;

import com.mano.projects.model.User;

public interface UserService {
	List<UserDto> getAllUsers();
	
	UserDto createUser(User user) throws Exception ;
	
	UserDto updateUser(int id, User user) throws Exception;
	
	void deleteUser(int id) throws Exception;
	
	UserDto getUserById(int id) throws Exception;
        
        boolean userAlreadyExist(String email);
        
        boolean userAlreadyExist(int id);
	
}
