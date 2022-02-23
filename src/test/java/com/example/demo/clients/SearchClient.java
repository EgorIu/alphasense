package com.example.demo.clients;

import com.example.demo.dto.SearchResultsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface SearchClient {

    @GET("/services/i/public-document-data/document/{docNumber}/keyword-search")
    Call<SearchResultsResponse> getSearchResults(@Path("docNumber") String userId, @QueryMap Map<String, String> options);

}
