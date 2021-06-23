package com.mano.projects.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mano.projects.configuration.JwtTokenUtil;
import com.mano.projects.model.CustomUserDetails;
import com.mano.projects.model.JwtResponseToken;
import com.mano.projects.model.User;
import com.mano.projects.repository.UserRepository;
import com.mano.projects.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class AuthController {
	
	private final UserRepository userRepository;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenUtil jwtTokenUtil;
	
	private final CustomUserDetailsService userDetailsService;
	
	@PostMapping(path = "authenticate", consumes = {"application/json"},produces= {"application/json"})
	public ResponseEntity createAuthenticationToken(@RequestBody User user) throws Exception {
		try{
			authenticate(user.getEmail(),user.getPassword());
		}
		catch(Exception e){
			System.out.println("Exception"+e);
			throw new Exception(e.getMessage());
		}
		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponseToken(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch(DisabledException e) {
			throw new Exception("USER_DISABLED",e);
		}
		catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
	
}
