package com.phosphenes.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"blogger_id","blog_name"}))
@JsonIgnoreProperties(value = {"new"})
public class Blog extends AbstractPersistable<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1983210128530397693L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blog_id")
	private Long id;
	
	@Column(name="blog_name", nullable = false)
	private String name;
	
	@Column(name="blog_content")
	private String content;
	
	@ManyToOne(targetEntity=Blogger.class)
	@JoinColumn(name="blogger_id")
	@JsonManagedReference
	private Blogger blogger;

	public Blog() {
		super();
	}

	public Blog(Long id, String name, String content, Blogger blogger) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.blogger = blogger;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}
	
}