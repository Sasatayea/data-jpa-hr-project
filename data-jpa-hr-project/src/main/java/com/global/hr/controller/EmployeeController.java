package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeResponse;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public EmployeeResponse findById(@PathVariable Long id) {
		
		Employee emp = employeeService.findById(id);
		
		EmployeeResponse res = new EmployeeResponse();
		res.setId(emp.getId());
		res.setName(emp.getName());
		res.setDepartment(emp.getDepartment());
//		res.setUser(emp.getUser());
		return res;
	}
	
	@GetMapping("/name/{name}")
	public List<Employee> findByName(@PathVariable String name) {
		
		return employeeService.findByName(name);
	}
	
	@GetMapping("/emp-dept")
	public List<Employee> findByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName) {
		
		return employeeService.findByEmpAndDept(empName, deptName);
	}
	
	@GetMapping("/count-emp-dept")
	public ResponseEntity<Long> countByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName) {
		
		return ResponseEntity.ok( employeeService.countByEmpAndDept(empName, deptName));
	}
	
	@DeleteMapping("/emp-dept")
	public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName) {
		
		return ResponseEntity.ok( employeeService.deleteByEmpAndDept(empName, deptName));
	}
	
	@PostMapping()
	public Employee insert (@RequestBody Employee emp) {
		
		return employeeService.insert(emp);
	}
	
	@PutMapping
	public Employee update (@RequestBody Employee emp) {
				
		return employeeService.update(emp);
	}
	
	@DeleteMapping("delete/{id}")
	public String  delete (@PathVariable Long id) {
		if(findById(id).getId() != null) {
			employeeService.deleteById(id);
			return "done" ;
		}else {
			return "Allredy removed" ;
		}
		 
	}
	
	@GetMapping("/department/{deptid}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptid) {
		
		return employeeService.findByDepartmentId(deptid);
	}
	
	@GetMapping("/All")
	public List<Employee> findAll() {
		
		return employeeService.findAll();
	}
}
