package com.example.demo.serviceimplementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LoginReq;
import com.example.demo.dto.request.registereq;
import com.example.demo.dto.response.LoginRes;
import com.example.demo.dto.response.registeres;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationServicee;
import com.example.demo.Repository.repo;
import com.example.demo.utils.JwtUtil;
import static com.example.demo.enumerated.Roles.USER;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class serimpl implements AuthenticationServicee {
    private final repo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public registeres register(registereq request) {
        Optional<User> isUserExists=userRepository.findByEmail(request.getEmail());
        if (isUserExists.isPresent()) {
            return registeres.builder().message("user with mail id"+request.getEmail()+" is already registered").build();
        }

        var user=User.builder()
        .name(request.getName())
        .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(USER)
            .build();
        userRepository.save(user);

        return registeres.builder().message("User Created successfully").build();
    }

    @Override
    public LoginRes login(LoginReq request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtUtil.generateToken(user);
        return LoginRes.builder()
            .message("User Login Successfully")
            .token(token)
            .build();
    }

}
