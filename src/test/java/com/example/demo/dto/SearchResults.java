package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchResults {
    private String cursorToken;
    private int originalStatementCount;
    private List<Statement> statements;
}
