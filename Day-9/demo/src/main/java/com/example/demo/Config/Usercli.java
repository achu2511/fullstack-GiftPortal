package com.example.demo.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Repository.repo;
import com.example.demo.model.User;
import static com.example.demo.enumerated.Roles.ADMIN;
import lombok.RequiredArgsConstructor;

@Component  //creating a container inside spring bean
@RequiredArgsConstructor //final key word
@SuppressWarnings("null")
public class Usercli implements CommandLineRunner{
    private final repo userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count()>0) 
            return;
        
        var user=User.builder() //import user from usermodel
            .name("Achyuth")
            .email("Achyuth@ticketgo.com")
            .password(passwordEncoder.encode("Ks@123"))
            .role(ADMIN)
            .build();
        
        userRepository.save(user);
        
    }
}
