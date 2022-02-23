package com.example.demo.utils;

import com.example.demo.pageobjects.DocPage;

import java.util.Map;

public class UrlUtils {

    private static final Map<Class<?>, String> URLS = Map.of(DocPage.class,  "/doc");

    public static String getUrl(Class<?> clazz){
        return URLS.get(clazz);
    }
}
