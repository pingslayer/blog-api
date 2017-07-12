package com.phosphenes.blog.service;

import com.phosphenes.blog.model.SearchResult;

public interface SearchService {

	SearchResult getAllResultsContaining(String keyword);
}
