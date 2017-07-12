package com.phosphenes.blog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phosphenes.blog.model.Blog;
import com.phosphenes.blog.model.Blogger;
import com.phosphenes.blog.model.SearchResult;
import com.phosphenes.blog.repository.BlogRepository;
import com.phosphenes.blog.repository.BloggerRepository;
import com.phosphenes.blog.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	BloggerRepository bloggerRepository;
	
	@Autowired
	BlogRepository blogRepository;

	@Override
	public SearchResult getAllResultsContaining(String keyword) {
		List<Blogger> bloggers = bloggerRepository.findByUsernameContaining("%" + keyword + "%");
		List<Blog> blogs = blogRepository.findByNameContaining("%" + keyword + "%");
		if(bloggers.isEmpty() && blogs.isEmpty()) {
			return null;
		}
		SearchResult searchResult = new SearchResult();
		searchResult.setBloggers(bloggers);
		searchResult.setBlogs(blogs);
		return searchResult;
	}

}
