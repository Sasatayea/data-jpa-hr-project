package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ActorProjection {
	
	
	Long getId();
	
	String getFirstName();
	
	String getLastName();
	
	@Value("#{target.firstName +' '+ target.lastName}")
	String getFullName();

}
