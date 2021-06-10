package com.mano.practice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class MvcImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcImplementationApplication.class, args);
		System.out.println("maoa"); 
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("addOldServerlet")
	public String add(HttpServletRequest req) {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		int num = i+j;
		HttpSession session = req.getSession();
		session.setAttribute("num", num);
		return "result.jsp";
	}
	
	@GetMapping("addSeesion")
	public String add(@RequestParam("num1") int i, @RequestParam("num2")int j,HttpSession session) {
		
		int num = i+j;
		session.setAttribute("num", num);
		return "result.jsp";
	}
	
	@GetMapping("addModelAndView")
	public ModelAndView add(@RequestParam("num1") int i, @RequestParam("num2")int j) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		int num = i+j;
		mv.addObject("num", num);
		return mv;
	}
	
	@GetMapping("addModelMap")
	public String add(@RequestParam("num1") int i, @RequestParam("num2")int j,ModelMap m) {
		int num = i+j;
		m.addAttribute("num", num);
		return "result";
	}
	
	@GetMapping("add")
	public String add(@RequestParam("num1") int i, @RequestParam("num2")int j,Model m) {
		int num = i+j;
		m.addAttribute("num", num);
		return "result";
	}

}
