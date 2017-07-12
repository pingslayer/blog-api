package com.phosphenes.blog.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.phosphenes.blog.model.Blogger;

public interface BloggerService extends UserDetailsService {
	
	public UserDetails loadUserByUsername(String username);
	
	public List<Blogger> getAllBloggers();

	public Blogger getBlogger(String bloggerName);

	public boolean isBloggerExist(String bloggerName);

	public void createBlogger(Blogger blogger);
	
	public void updateBlogger(String bloggerName, Blogger blogger);
	
	public void deleteBlogger(String bloggerName);
	
	public Boolean isBloggerValid(Blogger blogger);
	
	public Boolean isBloggerNameValid(String bloggerName);
}
