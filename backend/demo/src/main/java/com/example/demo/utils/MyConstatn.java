package com.example.demo.utils;

import java.util.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.http.HttpHeaders;

public class MyConstatn {
    public static final String AUTH="/api/v1/auth";
    public static final String REGISTER="/register";
    public static final String LOGIN="/login";
    public static final List<String> ORIGINS=Arrays.asList("https://localhost:400");
    public static final List<String> HEADERS=Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE);
    public static final List<String> METHODS=Arrays.asList(GET.name(),POST.name(),
    PUT.name(),PATCH.name(),DELETE.name(),HEAD.name());
}
