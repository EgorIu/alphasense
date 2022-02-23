package com.example.demo.services;

import com.example.demo.clients.SearchClient;
import com.example.demo.dto.SearchResultsResponse;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import retrofit2.Response;

import java.util.Map;

@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private SearchClient searchClient;

    @Override
    @SneakyThrows
    @Step("API. Search the following keyword {keyword} in the document {doc}")
    public Response<SearchResultsResponse> getSearchResults(String doc, String keyword, String slop, String released ) {
        Map<String,String> params = Map.of(
                "keyword",keyword,
                "slop",slop,
//                "positiveOnly","false",
//                "negativeOnly","false",
                "released",released
        );
        return searchClient.getSearchResults(doc, params).execute();
    }
}
