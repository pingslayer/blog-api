package com.phosphenes.blog.model;

import java.util.List;

public class SearchResult {
	
	private List<Blogger> bloggers;
	
	private List<Blog> blogs;

	public SearchResult() {
		super();
	}

	public SearchResult(List<Blogger> bloggers, List<Blog> blogs) {
		super();
		this.bloggers = bloggers;
		this.blogs = blogs;
	}

	public List<Blogger> getBloggers() {
		return bloggers;
	}

	public void setBloggers(List<Blogger> bloggers) {
		this.bloggers = bloggers;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
}
