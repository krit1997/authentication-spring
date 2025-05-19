package com.example.authentication.spring.boots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AuthenticationSpringBootsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationSpringBootsApplication.class, args);
	}

}
