package com.mano.projects.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mano.projects.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User getByUsername(String inputUser);

}
