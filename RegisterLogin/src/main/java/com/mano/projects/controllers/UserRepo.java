package com.mano.projects.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mano.projects.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User getByUsername(String inputUser);

}
