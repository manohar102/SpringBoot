package com.mano.projects.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mano.projects.models.User;

@Controller
public class HomeController {
	
	@Autowired
	UserRepo repo;
		
	@GetMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam("cpassword") String cpass, User user,Model m) {
		if(cpass.equals(user.getPassword())) {			
			User response = repo.save(user);
			if(response!=null) {
				m.addAttribute("result","Registration Completed! Please Login..!");
				m.addAttribute("error","");
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
	
	@PostMapping("/")
	public String loginUser(@RequestParam("username") String inputUser,@RequestParam("password") String inputPass,Model m) {
		User user = repo.getByUsername(inputUser);
		if(user==null) {
			m.addAttribute("error","Account Not Found");
		}
		else {
			if(inputPass.equals(user.getPassword())) {
				m.addAttribute("user",user.getFullname());
				return "profile.jsp";
			}
			else {
				m.addAttribute("error","Invalid Crediantials");
			}
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
