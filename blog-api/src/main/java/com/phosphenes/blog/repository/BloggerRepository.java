package com.phosphenes.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.phosphenes.blog.model.Blogger;

public interface BloggerRepository extends JpaRepository<Blogger, Long> {
	
	Blogger findOneByUsername(String username);
	
	List<Blogger> findByUsernameContaining(String bloggerName);

	Blogger findByUsername(String bloggerName);

	boolean existsByUsername(String bloggerName);

	void deleteByUsername(String bloggerName);

}
