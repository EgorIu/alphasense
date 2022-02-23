package com.example.demo.utils;


import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;


public class ApiUtils {

    public static <T> Converter<ResponseBody, T> getErrorConverter(Class<T> clazz, Retrofit retrofit) {
        return retrofit.responseBodyConverter(clazz, new Annotation[0]);
    }
}
