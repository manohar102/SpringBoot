package com.mano.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Poral";
	}
	
	@GetMapping("/hello")
	public Student hello(@RequestParam String id, String name) {
		return new Student(id,name);
	}
}
