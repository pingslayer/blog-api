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
import com.phosphenes.blog.model.Blogger;
import com.phosphenes.blog.security.resource.UserResourcePermission;
import com.phosphenes.blog.service.BloggerService;

@RestController
public class BloggerController {

	@Autowired
    private BloggerService bloggerService;
	
	@Autowired
	private UserResourcePermission userResourcePermission;
	
	/*******************************OPEN APIs*****************************************/
	
	/**
	 * GET ALL
	 * @return
	 */
	@RequestMapping(value="/bloggers", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blogger>> getAllBloggers() {
		List<Blogger> bloggers = bloggerService.getAllBloggers();
		if(bloggers.isEmpty()) {
			return new ResponseEntity<List<Blogger>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blogger>>(bloggers, HttpStatus.OK);
	}
	
	/**
	 * GET ONE
	 * @param bloggerName
	 * @return
	 */
	@RequestMapping(value="/bloggers/{bloggerName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Blogger> getBlogger(@PathVariable("bloggerName") String bloggerName) {
		Blogger blogger = bloggerService.getBlogger(bloggerName);
		if (blogger == null) {
            return new ResponseEntity<Blogger>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<Blogger>(blogger, HttpStatus.OK);
	}
	
	/**
	 * CREATE
	 * @param blogger
	 * @return
	 */
	@RequestMapping(value="/bloggers", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBlogger(@RequestBody Blogger blogger) {
		if(!bloggerService.isBloggerValid(blogger)) {
			return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(bloggerService.isBloggerExist(blogger.getUsername())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		bloggerService.createBlogger(blogger);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/*******************************PROTECTED APIs***********************************/
	
	/**
	 * UPDATE
	 * @param bloggerName
	 * @param blogger
	 * @return
	 */
	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_USER')")
	@RequestMapping(value="/bloggers/{bloggerName}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateBlogger(@PathVariable("bloggerName") String bloggerName, @RequestBody Blogger blogger) {
		if(!userResourcePermission.isAllowed(bloggerName)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		if (!bloggerService.isBloggerExist(bloggerName)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		bloggerService.updateBlogger(bloggerName, blogger);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * DELETE
	 * @param bloggerName
	 * @return
	 */
	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_USER')")
	@RequestMapping(value="/bloggers/{bloggerName}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBlogger(@PathVariable("bloggerName") String bloggerName) {
		if(!userResourcePermission.isAllowed(bloggerName)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		if (!bloggerService.isBloggerExist(bloggerName)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		bloggerService.deleteBlogger(bloggerName);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
