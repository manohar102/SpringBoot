package com.mano.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mano.projects.model.CustomUserDetails;
import com.mano.projects.model.User;
import com.mano.projects.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new CustomUserDetails(user);
	}
}