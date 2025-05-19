package com.example.authentication.spring.boots.services.auth;

import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;

@Service
public class UserDetailService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}