package com.global.hr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Actor;
import com.global.hr.entity.Employee;
import com.global.hr.projection.ActorProjection;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long>  {
	
	// this is the JPQL        sort from data.domain
		@Query(value = "select act from Actor act where (:actName is null or act.firstName like :actName)")
		Page<ActorProjection> filter(@Param("actName") String name , Pageable pageable);
}
