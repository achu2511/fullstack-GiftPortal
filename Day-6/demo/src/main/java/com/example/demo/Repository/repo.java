package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface repo extends JpaRepository<User,String> {
    Optional<User> findByEmail(String username);
}
