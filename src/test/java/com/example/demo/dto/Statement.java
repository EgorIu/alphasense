package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Statement {
    private String accessionNumber;
    private List<String> collapsedStatements;
    private String content;
    boolean context;
    private int page;
    private boolean recurring;
    private int snippetCount;
    private int snippetOffset;
    private String statementId;
    private int statementIndex;
    private int statementIndexOffset;

}
