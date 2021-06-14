package com.mano.projects.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mano.projects.models.User;

@Controller
public class HomeController {

	
	
	private BCryptPasswordEncoder encoder;
	
	public String encodePassword(String password) {
		if(encoder==null) {
			encoder = new BCryptPasswordEncoder();
		}
		String encodedPassword = encoder.encode(password);
		return encodedPassword;	
	}
	
	@Autowired
	UserRepo repo;
		
	@GetMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam("cpassword") String cpass, User user,Model m) {
		if(cpass.equals(user.getPassword())) {		
			user.setPassword(encodePassword(user.getPassword()));
			User response = repo.save(user);
			if(response!=null) {
				m.addAttribute("result","Registration Completed! Please Login..!");
				return "home.jsp";
			}
			else {
				m.addAttribute("error","Register Failed");
			}
		}
		else {
			m.addAttribute("error","Password didn't match");
		}
		return "register.jsp";
	}
	@RequestMapping("/profile")
	public String profile(Model m) {
		m.addAttribute("user","manoar");
		return "profile.jsp";
	}
	
	@PostMapping("/")
	public String loginUser(@RequestParam("username") String inputUser,@RequestParam("password") String inputPass,Model m) {
		User user = repo.getByUsername(inputUser);
		System.out.println("Error");
		if(user==null) {
			m.addAttribute("error","Account Not Found");
			System.out.println("Error2");
		}
		else {
			if(inputPass.equals(user.getPassword())) {
				m.addAttribute("user",user.getFullname());
				return "profile.jsp";
			}
			else {
				m.addAttribute("error","Invalid Crediantials");
			}
			System.out.println("Error3");
		}
		return "home.jsp";
	}

	@RequestMapping("/register")
	public String register(){
		return "register.jsp";
	}
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> users(){
		List<User> users = repo.findAll();
		return users;
	}
}
