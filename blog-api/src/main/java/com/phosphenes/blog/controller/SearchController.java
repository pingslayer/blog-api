package com.phosphenes.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.phosphenes.blog.model.SearchResult;
import com.phosphenes.blog.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/search/{keyword}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchResult> getAllResultsContaining(@PathVariable("keyword") String keyword) {
		SearchResult searchResult = searchService.getAllResultsContaining(keyword);
		if(searchResult == null) {
			return new ResponseEntity<SearchResult>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SearchResult>(searchResult, HttpStatus.OK);
	}

}
