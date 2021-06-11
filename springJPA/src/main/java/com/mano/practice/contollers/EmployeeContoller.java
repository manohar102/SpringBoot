package com.mano.practice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mano.practice.models.Employee;

@Controller
public class EmployeeContoller {
	
		@Autowired
		EmployeeRepo repo;
		
		
		@GetMapping("/")
		public String home() {
			return "index";
		}
		
		@PostMapping("addEmp")
		public String add(@ModelAttribute("emp") Employee emp) {
			repo.save(emp);
			return "result";
		}
		
		@GetMapping("getEmployees")
		public String getEmployees(Model m) {
			m.addAttribute("result",repo.findAll());
			return "employeeList";
		}
		
		@GetMapping("getEmployeeById")
		public String getEmployee(@RequestParam int eid,Model m) {
			
			m.addAttribute("emp",repo.getOne(eid));
			
			return "result";
		}

		
		@GetMapping("getEmployeeByName")
		public String getEmployee(@RequestParam String ename,Model m) {
			
			m.addAttribute("emp",repo.find(ename));
			
			return "result";
		}

}
