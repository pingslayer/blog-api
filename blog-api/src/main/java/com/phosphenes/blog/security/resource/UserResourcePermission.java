package com.phosphenes.blog.security.resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserResourcePermission {
	
	public Boolean isAllowed(String bloggerName) {
		return (bloggerName.equals(SecurityContextHolder.getContext().getAuthentication().getName()))? true: false;
	}
}
