package com.phosphenes.blog.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phosphenes.blog.model.Blog;
import com.phosphenes.blog.model.Blogger;
import com.phosphenes.blog.repository.BlogRepository;
import com.phosphenes.blog.repository.BloggerRepository;
import com.phosphenes.blog.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	BloggerRepository bloggerRepository;
	
	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}
	
	@Override
	public List<Blog> getBlogs(String blogName) {
		return blogRepository.findByName(blogName);
	}
	
	@Override
	public List<Blog> getAllBlogsOfBlogger(String bloggerName) {
		return blogRepository.findAllByBloggerUsername(bloggerName);
	}

	@Override
	public Blog getBlog(String bloggerName, String blogName) {
		return blogRepository.findByNameAndBloggerUsername(blogName, bloggerName);
	}

	@Override
	public boolean isBlogExist(String bloggerName, String blogName) {
		return blogRepository.existsByNameAndBloggerUsername(blogName, bloggerName);
	}
	
	@Override
	public void createBlog(String bloggerName, Blog blog) {
		blog.setId(null);
		Blogger blogger = bloggerRepository.findByUsername(bloggerName);
		blog.setBlogger(blogger);
		blogRepository.save(blog);
	}
	
	@Override
	public void updateBlog(String bloggerName, String blogName, Blog blog) {
		Blog currentBlog = blogRepository.findByNameAndBloggerUsername(blogName, bloggerName);
		BeanUtils.copyProperties(blog, currentBlog, "id", "blogger");
		blogRepository.save(currentBlog);
	}

	@Override
	public void deleteBlog(String bloggerName, String blogName) {
		blogRepository.deleteByNameAndBloggerUsername(blogName, bloggerName);
	}

	@Override
	public Boolean isBloggerNameValid(String bloggerName) {
		if(bloggerName.equals(null) || bloggerName.isEmpty() || bloggerName.equals(""))
			return false;
		return true;
	}

}
