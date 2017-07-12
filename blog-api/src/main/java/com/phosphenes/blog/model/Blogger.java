package com.phosphenes.blog.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Blogger implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1697320607747157298L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blogger_id")
	private Long id;
	
	@OneToMany(targetEntity=Blog.class, mappedBy="blogger", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@JsonBackReference
	private Set<Blog> blogs;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "authorities", nullable = false)
	@OneToMany(targetEntity=Authority.class, mappedBy="blogger", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<GrantedAuthority> authorities;

	public Blogger() {
		super();
	}
	
	public Blogger(Long id, Set<Blog> blogs, String username, String password, boolean enabled,
			List<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.blogs = blogs;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Blog> getBlogs() {
		return blogs;
	}
	
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@JsonSetter
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
