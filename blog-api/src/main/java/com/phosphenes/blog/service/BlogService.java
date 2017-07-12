package com.phosphenes.blog.service;

import java.util.List;

import com.phosphenes.blog.model.Blog;

public interface BlogService {
	
	public List<Blog> getAllBlogs();
	
	public List<Blog> getBlogs(String blogName);
	
	public List<Blog> getAllBlogsOfBlogger(String bloggerName);

	public Blog getBlog(String bloggerName, String blogName);
	
	public boolean isBlogExist(String bloggerName, String blogName);
	
	public void createBlog(String bloggerName, Blog blog);
	
	public void updateBlog(String bloggerName, String blogName, Blog blog);
	
	public void deleteBlog(String bloggerName, String blogName);
	
	public Boolean isBloggerNameValid(String bloggerName);
	
}
