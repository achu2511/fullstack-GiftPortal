package com.giftvoucher.Achu.controller;

import static com.giftvoucher.Achu.utils.MyConstant.AUTH;
import static com.giftvoucher.Achu.utils.MyConstant.LOGIN;
import static com.giftvoucher.Achu.utils.MyConstant.REGISTER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftvoucher.Achu.Repository.CustomerRepository;
import com.giftvoucher.Achu.dto.request.LoginRequest;
import com.giftvoucher.Achu.dto.request.RegisterRequest;
import com.giftvoucher.Achu.dto.response.LoginResponse;
import com.giftvoucher.Achu.dto.response.RegisterResponse;
import com.giftvoucher.Achu.models.Customer;
import com.giftvoucher.Achu.models.User;
import com.giftvoucher.Achu.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000") 
public class AuthenticationController {

    private final AuthenticationService authenticationService;

   

  
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        RegisterResponse response=new RegisterResponse();
        try {
            response=authenticationService.register(request);
            return new ResponseEntity<>(response,ACCEPTED);

        } catch (Exception e) {
            response.setMessage("Something went wrong");
            return new ResponseEntity<>(response,EXPECTATION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response= new LoginResponse();
        try {
            response=authenticationService.login(request);
            return new ResponseEntity<>(response,ACCEPTED);
        } catch (Exception e) {
            LoginResponse.builder()
                .message("Something went wrong")
                .token("")
                .build();
            return new ResponseEntity<>(response,EXPECTATION_FAILED);
        }
    }
}
