package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo ;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private UserService userService ;
	
	public Employee findById(Long id) {
		
		return employeeRepo.findById(id).orElseThrow();
	}
	
	public List<Employee> findBySalary (Double salary){
		
		return employeeRepo.findBySalary(salary);
	}
	
	public List<Employee> findByName(String name) {
		
		return employeeRepo.findByName(name);
	}
	
	public List<Employee> findByEmpAndDept(String empName ,String deptName) {
		
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Long countByEmpAndDept(String empName ,String deptName) {
		
		return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	public Long deleteByEmpAndDept(String empName ,String deptName) {
		
		return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Employee insert (Employee emp) {
		
		if(emp.getUser() != null && emp.getUser().getId() != null) {
			
			emp.setUser(userService.findById(emp.getUser().getId()));
		}
		
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		return employeeRepo.save(emp);
	}
	
	public Employee update (Employee emp) {
		
		Employee currant = employeeRepo.findById(emp.getId()).get();
		
		currant.setName(emp.getName());
		currant.setSalary(emp.getSalary());
		currant.setDepartment(emp.getDepartment());
		
		return employeeRepo.save(currant);
	}
	
	public List<Employee> findByDepartmentId(Long id) {
		
		return employeeRepo.findByDepartment(id);
	}
	
	
	public List<Employee> findAll() {
		
		return employeeRepo.findAll();
	}
	
	public void deleteById(Long id) {
		
		employeeRepo.deleteById(id);
	}
	
	public HRStatisticProjection  getHRStatistic() {
	    return employeeRepo.getHRStatistic() ;
	}
}
