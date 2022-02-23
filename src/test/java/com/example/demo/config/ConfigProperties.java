package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "alphasense")
@Data
public class ConfigProperties {

    private String baseUrl;
    private String docName;
}

