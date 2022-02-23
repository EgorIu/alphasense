package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private int statusCode;
    private List<String> message;
    private String error;
}
