package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Actor;
import com.global.hr.projection.ActorProjection;
import com.global.hr.service.ActorService;

@RestController
@RequestMapping("/actor")
public class ActorController {

	@Autowired 
	ActorService actorService ;
	
	@GetMapping("/All")
	public List<Actor> findAll (){
		return actorService.findAll() ;
	}
	
	@GetMapping("/filter")
	public  ResponseEntity<?> filter(@RequestParam String name ,@RequestParam int pageNum ,@RequestParam int pageSize ,
							  @RequestParam String sortcol , @RequestParam Boolean isAsc){
		
		return  ResponseEntity.ok( actorService.filter(name ,pageNum ,pageSize,sortcol , isAsc) );
	}
}
