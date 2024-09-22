package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	List<Employee> findBySalary (Double salary);
	
//	List<Employee> findByDepartment(Long deptId);
	
	List<Employee> findByName (String name);
	
	List<Employee> findByDepartmentId(Long id) ;
	
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;

	Long countByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;
	
	@Modifying(clearAutomatically = true , flushAutomatically = true)
	@Transactional
	Long deleteByNameContainingAndDepartmentNameContaining(String empName ,String deptName) ;
	
	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")  // شغالهه
	List<Employee> findByDepartment(Long deptId) ;
	
	// this is the JPQL 
	@Query(value = "select emp from Employee emp where emp.name like :empName")
	List<Employee> filter(@Param("empName") String name);
		
	// this is the sql native 
	@Query(value = "select * from hr_employee emp where emp.emp_name like :empName", nativeQuery = true)
	List<Employee> filterNative(@Param("empName") String name);
		
	@Query(value = "select (select count(*) from hrglobal.hr_departments) deptCount,"
			+ "(select count(*) from hrglobal.hr_employee) empCount,"
			+ "(select count(*) from hrglobal.sec_users) userCount" 
			, nativeQuery = true)
	HRStatisticProjection  getHRStatistic() ;
}
