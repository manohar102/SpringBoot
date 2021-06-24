package com.mano.projects;

//import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mano.projects.dto.mapper.UserMapper;

@SpringBootApplication
public class SecondDemoProjectApplication {

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
//	
//	@Bean
//	public UserMapper userMapper() {
//		return new UserMapper();
//	}
//	
	public static void main(String[] args) {
		SpringApplication.run(SecondDemoProjectApplication.class, args);
	}

}
