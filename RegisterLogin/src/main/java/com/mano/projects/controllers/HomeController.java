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
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
//	
//	public String encodePassword(String password) {
//		String encodedPassword = encoder.encode(password);
//		return encodedPassword;
//	}
	
	@Autowired
	UserRepo repo;
		
	@GetMapping("/")
	@ResponseBody
	public String man() {
		return "try /login";
	}
	
	@RequestMapping("/login")
	public String home() {
		return "login.jsp";
	}
	
	@GetMapping("/register" )
	public String register(){
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam("cpassword") String cpass, User user, Model m) {
		System.out.println(user.getUsername()+"--"+user.getPassword());
		
		if(cpass.equals(user.getPassword())) {	
			User response = repo.save(user);
			
			if(response!=null) {
				m.addAttribute("result","Registration Completed! Please Login..!");
				return "login.jsp";
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
	
	
	
//	@PostMapping("/login")
//	public String loginUser(@RequestParam("username") String inputUser,@RequestParam("password") String inputPass,Model m) {
//		User user = repo.getByUsername(inputUser);
//		if(user==null) {
//			m.addAttribute("error","Account Not Found");
//			return "login.jsp";
//		}
//		else {
//			if(inputPass.equals(user.getPassword())) {
//				m.addAttribute("user",user.getFullname());
//				return "profile.jsp";
//			}
//			else {
//				m.addAttribute("error","Invalid Crediantials");
//			}
//		}
//		return "home.jsp";
//	}

	
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> users(){
		List<User> users = repo.findAll();
		return users;
	}
}
