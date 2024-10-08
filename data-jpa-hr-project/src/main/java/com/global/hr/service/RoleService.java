package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Role;
import com.global.hr.repository.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo ;
	
	public Role findById(Long id) {
		
		return roleRepo.findById(id).orElseThrow();
	}
	
	public Role insert (Role role) {
		
		return roleRepo.save(role);
	}
	
	public Role update (Role role) {
		
		Role currant = roleRepo.findById(role.getId()).get();
		
		currant.setName(role.getName());
		
		return roleRepo.save(currant);
	}
	
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	
	public Role findByName(String name) {		
		return roleRepo.findByName(name);
	}
	
	public void deleteById(Long id) {
		
		roleRepo.deleteById(id);
	}
}
