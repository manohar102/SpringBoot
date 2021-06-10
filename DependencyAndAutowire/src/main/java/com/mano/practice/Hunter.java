package com.mano.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hunter {
	
	@Autowired
	Gun gun;
	public void hunt() {
		gun.shoot();
	}
}
