package com.example.authentication.spring.boots.services.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.authentication.spring.boots.dtos.auth.ForgetPasswordRequest;
import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;

@Service
public class ForgetPasswordService {
    private final UserRepository userRepository;

    public ForgetPasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String execute(ForgetPasswordRequest request) {
        Optional<User> dataUser = userRepository.findByUsernameOrEmail(
                request.getEmail(), request.getEmail());
        if (dataUser.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email Not Found");
        }

        return "Email found. Proceeding with reset.";
    }
}
