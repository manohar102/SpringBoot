package com.example.mano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ManoApplication {
    public static void main(String[] args) {
            SpringApplication.run(ManoApplication.class, args);
    }
    
    private static final String template = "Hello, %s!";
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }
   
    
    @GetMapping("/Student")
    public Student greeting(@RequestParam String id, String name ) {
            return new Student(id,name);
    }
}
