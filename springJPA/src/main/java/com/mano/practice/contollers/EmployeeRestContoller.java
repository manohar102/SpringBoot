package com.mano.practice.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mano.practice.models.Employee;


@RestController
public class EmployeeRestContoller {
	
	@Autowired
	EmployeeRepo repo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="Employees", produces= {"a	pplication/json"})
	public List<Employee> Employees() {
		
		List<Employee> employees = repo.findAll();
		
		return employees;
	}
	
	@GetMapping("Employees/{eid}")
	public Employee getEmployee(@PathVariable("eid") int eid) {
			
		Employee emp = repo.findById(eid).orElse(new Employee(0,""));
		return emp;
	}

	@PostMapping(path = "Employee", consumes = {"application/json"})
	public Employee addEmployee(@RequestBody Employee employee) {
		
		repo.save(employee);
		
		return employee;
	}
	
}
