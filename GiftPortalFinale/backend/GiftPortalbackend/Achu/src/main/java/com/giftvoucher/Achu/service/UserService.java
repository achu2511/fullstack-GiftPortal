package com.giftvoucher.Achu.service;

import com.giftvoucher.Achu.dto.response.BasicResponse;
import com.giftvoucher.Achu.dto.response.UserResponse;

public interface UserService {

    BasicResponse<UserResponse> getAllUser();

}