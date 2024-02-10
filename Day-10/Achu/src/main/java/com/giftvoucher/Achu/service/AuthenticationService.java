package com.giftvoucher.Achu.service;



import com.giftvoucher.Achu.dto.request.LoginRequest;
import com.giftvoucher.Achu.dto.request.RegisterRequest;
import com.giftvoucher.Achu.dto.response.LoginResponse;
import com.giftvoucher.Achu.dto.response.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
