/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.controllers;

import com.notes.DTO.UserDTO;
import com.notes.repository.UserRepository;
import com.notes.services.UserService;
import com.notes.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        System.out.println("Post Enter");
        if(userService.getUserByEmail(user.getEmail())!=null)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
        userRepository.save(user);
        System.out.println("user saved");
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") String email)
    {
        if(email==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return new ResponseEntity(userDTO,HttpStatus.OK);
        
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email")String email)
    {
        User user = userService.getUserByEmail(email);
        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email,@RequestBody User newUser)
    {
        User oldUser = userService.getUserByEmail(email);
        oldUser.setEmail(newUser.getEmail());
        oldUser.setFirst_name(newUser.getFirst_name());
        oldUser.setLast_name(newUser.getLast_name());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userService.saveUser(oldUser);
        return new ResponseEntity(HttpStatus.OK);
    }
            
    
    
}
