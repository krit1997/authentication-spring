package com.example.authentication.spring.boots.services.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;
import com.example.authentication.spring.boots.services.mapResponse.response.UserSimpleResponse;

@Service
public class ListUsersService {
    private final UserRepository userRepository;

    public ListUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserSimpleResponse> listUser() {
        List<User> users = userRepository.findAllActive().orElse(List.of());
        return users.stream()
                .map(user -> new UserSimpleResponse(
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName()))
                .collect(Collectors.toList());
    }
}
