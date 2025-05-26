package com.example.authentication.spring.boots.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {
    @Value("${jwt.reset.secret}")
    private String resetSecret;

    public String getResetSecret() {
        return resetSecret;
    }
}
