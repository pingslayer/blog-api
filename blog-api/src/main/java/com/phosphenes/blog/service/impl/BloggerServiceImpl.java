package com.phosphenes.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phosphenes.blog.model.Authority;
import com.phosphenes.blog.model.Blogger;
import com.phosphenes.blog.repository.BloggerRepository;
import com.phosphenes.blog.service.BloggerService;

@Service("userDetailsService")
@Transactional
public class BloggerServiceImpl implements BloggerService {
	
	@Autowired
	private BloggerRepository bloggerRepository;
	
	private PasswordEncoder passwordEncoder;
		
	public BloggerServiceImpl() {
		super();
		passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return bloggerRepository.findOneByUsername(username);
	}
	
	@Override
	public List<Blogger> getAllBloggers() {
		return bloggerRepository.findAll();
	}

	@Override
	public Blogger getBlogger(String bloggerName) {
		return bloggerRepository.findByUsername(bloggerName);
	}

	@Override
	public boolean isBloggerExist(String bloggerName) {
		return bloggerRepository.existsByUsername(bloggerName);
	}

	@Override
	public void createBlogger(Blogger blogger) {
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setBlogger(blogger);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
		blogger.setAuthorities(authorities);
		blogger.setEnabled(true);
		blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
		bloggerRepository.save(blogger);
	}
	
	@Override
	public void updateBlogger(String bloggerName, Blogger blogger) {
		Blogger currentBlogger = bloggerRepository.findByUsername(bloggerName);
		BeanUtils.copyProperties(blogger, currentBlogger, "id");
		bloggerRepository.save(currentBlogger);
	}

	@Override
	public void deleteBlogger(String bloggerName) {
		bloggerRepository.deleteByUsername(bloggerName);
	}

	@Override
	public Boolean isBloggerValid(Blogger blogger) {
		if(blogger.getUsername().equals(null) || blogger.getUsername().isEmpty() || blogger.getUsername().equals(""))
			return false;
		if(blogger.getPassword().equals(null) || blogger.getPassword().isEmpty() || blogger.getPassword().equals(""))
			return false;
		return true;
	}

	@Override
	public Boolean isBloggerNameValid(String bloggerName) {
		if(bloggerName.equals(null) || bloggerName.isEmpty() || bloggerName.equals(""))
			return false;
		return true;
	}

}
