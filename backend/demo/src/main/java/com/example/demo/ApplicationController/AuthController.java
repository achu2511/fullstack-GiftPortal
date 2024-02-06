package com.example.demo.ApplicationController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.LoginReq;
import com.example.demo.dto.request.registereq;
import com.example.demo.dto.response.LoginRes;
import com.example.demo.dto.response.registeres;
import com.example.demo.service.AuthenticationServicee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static com.example.demo.utils.MyConstatn.REGISTER;
import static com.example.demo.utils.MyConstatn.LOGIN;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationServicee authenticationService;

  
    @PostMapping(REGISTER)
    public ResponseEntity<registeres> register(@RequestBody registereq request){
        registeres response=new registeres();
        try {
            response=authenticationService.register(request);
            return new ResponseEntity<>(response,ACCEPTED);

        } catch (Exception e) {
            response.setMessage("Something went wrong");
            return new ResponseEntity<>(response,EXPECTATION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq request){
        LoginRes response= new LoginRes();
        try {
            response=authenticationService.login(request);
            return new ResponseEntity<>(response,ACCEPTED);
        } catch (Exception e) {
            LoginRes.builder()
                .message("Something went wrong").token("").build();
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
        }
    }
}
