package com.example.authentication.spring.boots.services.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.authentication.spring.boots.dtos.auth.LoginRequest;
import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(request.getUsername(),
                request.getUsername());
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
        if (!user.getRole().equals("Admin")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You do not have access rights");
        }
        return user; // หรือ return JWT
    }
}
