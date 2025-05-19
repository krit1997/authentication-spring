package com.example.authentication.spring.boots.services.mapResponse.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSimpleResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
