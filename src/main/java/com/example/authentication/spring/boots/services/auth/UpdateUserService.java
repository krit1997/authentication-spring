package com.example.authentication.spring.boots.services.auth;

import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.dtos.auth.UpdateUserRequest;
import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;

@Service
public class UpdateUserService {
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;

    public UpdateUserService(UserRepository userRepository, UserDetailService userDetailService) {
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
    }

    public User execute(String id, UpdateUserRequest request) {
        User dataUser = userDetailService.execute(id);

        dataUser.setFirstName(request.getFirstName());
        dataUser.setLastName(request.getLastName());

        return userRepository.save(dataUser);
    }
}
