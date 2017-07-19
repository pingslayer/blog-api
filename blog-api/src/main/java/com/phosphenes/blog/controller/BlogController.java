package com.phosphenes.blog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.phosphenes.blog.model.Blog;
import com.phosphenes.blog.security.resource.UserResourcePermission;
import com.phosphenes.blog.service.BlogService;
import com.phosphenes.blog.service.BloggerService;

@RestController
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@Autowired
    BloggerService bloggerService;
	
	@Autowired
	private UserResourcePermission userResourcePermission;
	
	/*******************************OPEN APIs*****************************************/
	
	/**
	 * GET ALL RELATED TO BLOGGER
	 * @param bloggerName
	 * @return
	 */
	@RequestMapping(value="/bloggers/{bloggerName}/blogs", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getAllBlogsOfBlogger(@PathVariable("bloggerName") String bloggerName) {
		if(!bloggerService.isBloggerExist(bloggerName)) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
		}
		List<Blog> blogs = blogService.getAllBlogsOfBlogger(bloggerName);
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	/**
	 * GET ONE
	 * @param bloggerName
	 * @param blogName
	 * @return
	 */
	@RequestMapping(value="/bloggers/{bloggerName}/blogs/{blogName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Blog> getBlog(@PathVariable("bloggerName") String bloggerName, @PathVariable("blogName") String blogName) {
		Blog blog = blogService.getBlog(bloggerName, blogName);
		if (blog == null) {
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	/*******************************PROTECTED APIs***********************************/
	
	/**
	 * CREATE
	 * @param bloggerName
	 * @param blog
	 * @return
	 */
    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_USER')")
	@RequestMapping(value="/bloggers/{bloggerName}/blogs", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBlog(@PathVariable("bloggerName") String bloggerName, @RequestBody Blog blog) {
    	if(!userResourcePermission.isAllowed(bloggerName)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		if(blogService.isBlogExist(bloggerName, blog.getName())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		blogService.createBlog(bloggerName, blog);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * UPDATE
	 * @param bloggerName
	 * @param blogName
	 * @param blog
	 * @return
	 */
    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_USER')")
	@RequestMapping(value="/bloggers/{bloggerName}/blogs/{blogName}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateBlog(@PathVariable("bloggerName") String bloggerName, @PathVariable("blogName") String blogName, @RequestBody Blog blog) {
    	if(!userResourcePermission.isAllowed(bloggerName)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		if(!blogService.isBlogExist(bloggerName, blogName)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		blogService.updateBlog(bloggerName, blogName, blog);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * DELETE
	 * @param bloggerName
	 * @param blogName
	 * @return
	 */
    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_USER')")
	@RequestMapping(value="/bloggers/{bloggerName}/blogs/{blogName}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBlog(@PathVariable("bloggerName") String bloggerName, @PathVariable("blogName") String blogName) {
    	if(!userResourcePermission.isAllowed(bloggerName)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
    	if(!blogService.isBlogExist(bloggerName, blogName)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		blogService.deleteBlog(bloggerName, blogName);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}