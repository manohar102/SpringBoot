package com.mano.projects.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mano.projects.dto.UserDto;
import com.mano.projects.model.User;
import com.mano.projects.repository.UserRepository;
import com.mano.projects.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {

	private final ModelMapper modelMapper;

	private final UserService userService;
	
	@PostMapping(path="register", consumes={"application/json"}, produces= {})
	private ResponseEntity<String> registerUser(User user){
		
		return new ResponseEntity("Registration Completed Successfully",HttpStatus.OK);
	}
	
	@GetMapping("users")
	public List<UserDto> users(){
		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}
}

