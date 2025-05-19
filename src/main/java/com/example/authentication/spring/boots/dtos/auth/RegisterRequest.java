package com.example.authentication.spring.boots.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email
    @NotBlank
    @Schema(example = "krit.t@extend-it-resource.com")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$", message = "Password must contain uppercase, lowercase, number, and special character")
    @Schema(example = "P@ssw0rd")
    private String password;

    @NotBlank
    @Schema(example = "krit")
    private String firstName;

    @NotBlank
    @Schema(example = "thanadka")
    private String lastName;

    @NotBlank
    @Schema(example = "Admin")
    private String role;
}