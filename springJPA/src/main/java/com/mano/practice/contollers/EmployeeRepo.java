package com.mano.practice.contollers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mano.practice.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

//	List<Employee> findByName(String ename);
	
	@Query("from Employee where name= :ename")
	List<Employee> find(@Param("ename") String ename);

}
