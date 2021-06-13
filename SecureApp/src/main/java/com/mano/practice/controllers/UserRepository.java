package com.mano.practice.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mano.practice.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
		 User findByUsername(String username);
}
