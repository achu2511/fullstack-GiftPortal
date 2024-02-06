package com.example.demo.service;
import com.example.demo.dto.request.LoginReq;
import com.example.demo.dto.request.registereq;
import com.example.demo.dto.response.LoginRes;
import com.example.demo.dto.response.registeres;


public interface AuthenticationServicee {
    registeres register(registereq request);

    LoginRes login(LoginReq request);
}
