package com.mano.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyAndAutowireApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DependencyAndAutowireApplication.class, args);
		Hunter hunter = context.getBean(Hunter.class);
		hunter.hunt();	
		
		Hunter hunter2 = context.getBean(Hunter.class);
		hunter2.hunt();
	}
	
	
	
	

}
