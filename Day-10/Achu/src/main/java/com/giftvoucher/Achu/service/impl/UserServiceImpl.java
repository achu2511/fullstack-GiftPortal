package com.giftvoucher.Achu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.giftvoucher.Achu.Repository.UserRepository;
import com.giftvoucher.Achu.dto.response.BasicResponse;
import com.giftvoucher.Achu.dto.response.UserResponse;
import com.giftvoucher.Achu.models.User;
import com.giftvoucher.Achu.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository rep;

    @Override
    public BasicResponse<UserResponse> getAllUser() {

        List<User>users=rep.findAll();

        // stream used to convert list to obj
        List<UserResponse> userresp =users.stream().map(user->UserResponse.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .role(user.getRole())
        .build())
        .collect(Collectors.toList());

        return BasicResponse.<UserResponse>builder()
        .message("user data fetched successfully")
        .data(userresp)
        .build();      
    }  
}