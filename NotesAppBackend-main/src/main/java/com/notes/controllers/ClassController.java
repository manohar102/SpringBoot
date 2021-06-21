package com.notes.controllers;

import com.google.gson.JsonObject;

import java.util.Set;
import com.notes.services.*;
import com.notes.configuration.JwtTokenProvider;
import com.notes.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.repository.UserRepository;
//import com.notes.services.ServiceClass;
import com.notes.services.UserService;
import com.notes.model.Note;
import com.notes.model.User;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;


@RestController
public class ClassController {

     @Autowired
     AuthenticationManager authenticationManager;
	@Autowired
	UserRepository repo;
	@Autowired 
	UserService userService;
        @Autowired
        MyUserDetailsService myUserDetailsService;
        @Autowired
        JwtTokenProvider jwtTokenProvider;
        @Bean
        PasswordEncoder encoder()
        {
            return new BCryptPasswordEncoder();
        }

        @GetMapping("/test")
        public String test()
        {
            return "test";
        }
        @PostMapping("authenticate")
	public ResponseEntity createAuthenticationToken(@RequestBody User user) throws Exception {
            UsernamePasswordAuthenticationToken UPAuthToken;
		try{
                    UPAuthToken = authenticate(user.getEmail(),user.getPassword());
			
		}
		catch(Exception e){
			System.out.println("Exception"+e);
			throw new Exception(e.getMessage());
		}
		final MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
		final String token = jwtTokenProvider.generateToken(UPAuthToken);
                 
		return ResponseEntity.ok(new JwtResponseToken(token));
	}
	
	private UsernamePasswordAuthenticationToken authenticate(String username, String password) throws Exception {
            UsernamePasswordAuthenticationToken UPAuthToken;
		try {
                        UPAuthToken = new UsernamePasswordAuthenticationToken(username,password);
			authenticationManager.authenticate(UPAuthToken);
                        return UPAuthToken;
                        
		}
		catch(DisabledException e) {
			throw new Exception("USER_DISABLED",e);
		}
		catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
        
        @GetMapping("/signin")
        public ResponseEntity login(Principal principal)
        {


//            User userDetails = userService.getUserByEmail(user.getEmail());
            if(principal==null)
            {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

//            if(encoder().matches(userDetails.getPassword(),user.getPassword()))
//            {
                

                      Map<String,Object> map = new HashMap<>();

                    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;

                    System.out.println(token.getName());

                    User u = userService.getUserByEmail(token.getName());
                    
                    map.put("uid",u.getUid());
                    map.put("first_name",u.getFirst_name());
                    map.put("last_name",u.getLast_name());
                    map.put("email",u.getEmail());

                    
                   System.out.println(u);
                   System.out.println(new Date(System.currentTimeMillis()+1000));
                   
                   String JwtToken = jwtTokenProvider.generateToken(token);
                   
                   System.out.println("true");
                    map.put("token",JwtToken);

                    return new ResponseEntity(map,HttpStatus.OK);
                
//                catch(Exception e){
//                    System.out.println(8);
//                    System.out.println(e);
//                }
//                
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                
        }
        @PostMapping("/register")
        public ResponseEntity register(@RequestBody User u)
        {
            User tempUser = userService.getUserByEmail(u.getEmail());
            if(tempUser!=null)
            {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
                
            }
            try
            {
                u.setPassword(encoder().encode(u.getPassword()));
                this.repo.save(u);
                return new ResponseEntity(HttpStatus.OK);
            }
            catch(Exception e){}
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	

	



}
