package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	List<Employee> findByName (String name);
	
	List<Employee> findByDepartmentId(Long id) ;
	
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;

	Long countByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;
	
	@Modifying(clearAutomatically = true , flushAutomatically = true)
	@Transactional
	Long deleteByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;
	
	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")  // شغالهه
	List<Employee> findByDepartment(Long deptId) ;

}
