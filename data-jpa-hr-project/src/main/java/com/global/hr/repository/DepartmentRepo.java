package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.hr.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
}
