package com.global.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Actor;
import com.global.hr.projection.ActorProjection;
import com.global.hr.repository.ActorRepo;
@Service
public class ActorService {
	
	@Autowired
	ActorRepo actorRepo ;
	
	public List<Actor> findAll (){
		
		return actorRepo.findAll() ;
	}
	
	public Page<ActorProjection> filter (String name ,int pageNum , int pageSize,  String sortcol ,Boolean isAsc){
		
		if(name.isEmpty() || name.isBlank() || name == null) {
			name = null ;
		}
		
//		List<Order> ordars = new ArrayList<Order>() ;
//		
//		Order order1 = new Order(isAsc?Direction.ASC: Direction.DESC, sortcol);
//		
//		ordars.add(order1);
		
		Pageable page = PageRequest.of(pageNum, pageSize, Sort.by ( isAsc?Direction.ASC: Direction.DESC ,sortcol )) ;
		// Direction.ASC
		return actorRepo.filter(name , page) ;
	}

}
