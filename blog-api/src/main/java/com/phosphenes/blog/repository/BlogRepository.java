package com.phosphenes.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.phosphenes.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	List<Blog> findByNameContaining(String blogName);
	
	List<Blog> findByName(String blogName);
	
	List<Blog> findAllByBloggerUsername(String bloggerName);

	Blog findByNameAndBloggerUsername(String blogName, String bloggerName);

	boolean existsByNameAndBloggerUsername(String blogName, String bloggerName);

	void deleteByNameAndBloggerUsername(String blogName, String bloggerName);
	
}
