package com.mano.projects.controller;

import java.util.List;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mano.projects.dto.UserDto;
import com.mano.projects.dto.mapper.UserMapper;
import com.mano.projects.model.User;
import com.mano.projects.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@RestController @RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {

//	private final ModelMapper modelMapper;
	
	private final UserMapper userMapper;

	private final UserService userService;
        
	
	@PostMapping(path="register",consumes = {"application/json"},produces= {"application/json"})
	private ResponseEntity<UserDto> registerUser(@RequestBody User user){
                UserDto userDto = null;
                if(userService.userAlreadyExist(user.getEmail())){
                    return new ResponseEntity("User Already Found",HttpStatus.CONFLICT); 
                }
                try{
                    userDto = userService.createUser(user);
                }
                catch(Exception error){
                    return new ResponseEntity(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
		return new ResponseEntity(userDto,HttpStatus.OK);
	}
        
        @GetMapping(path="{uid}")
        private ResponseEntity<UserDto> getUser(@PathVariable("uid") int uid){
            UserDto userDto = null;
            try{
                userDto = userService.getUserById(uid);
            }
            catch(Exception error){
                return new ResponseEntity(error.getMessage(),HttpStatus.CONFLICT);
            }
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
	
        @PutMapping(path="{uid}", consumes={"application/json"})
        private ResponseEntity<UserDto> updateUser(@PathVariable("uid") int uid, @RequestBody User user){
            UserDto userDto = null;
            if(!userService.userAlreadyExist(uid)){
                return new ResponseEntity("User Not Found",HttpStatus.CONFLICT); 
            }
            try{
                userDto = userService.updateUser(uid, user);
            }
            catch(Exception error){
                return new ResponseEntity(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        
        @DeleteMapping(path="{uid}")
        private ResponseEntity<String> deleteUser(@PathVariable("uid") int uid){
           if(!userService.userAlreadyExist(uid)){
                return new ResponseEntity("User Not Found",HttpStatus.CONFLICT); 
           }
           try{
                userService.deleteUser(uid);
            }
            catch(Exception error){
                return new ResponseEntity(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           }
           return new ResponseEntity("User Deleted Successfully", HttpStatus.OK);
        }
        
	@GetMapping("users")
	public List<UserDto> users(){
		return userService.getAllUsers();
	}
}

