package com.phosphenes.blog.security.resource;

import java.security.Principal;
import org.springframework.stereotype.Component;

@Component
public class UserResourcePermission {
	
	public Boolean isAllowed(Principal principal, String bloggerName) {
		return (bloggerName.equals(principal.getName()))? true: false;
	}
}
