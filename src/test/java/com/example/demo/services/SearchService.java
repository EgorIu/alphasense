package com.example.demo.services;

import com.example.demo.dto.SearchResultsResponse;
import retrofit2.Response;

public interface SearchService {
    Response<SearchResultsResponse> getSearchResults(String doc, String keyword, String slop, String released);
}
