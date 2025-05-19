package com.example.authentication.spring.boots.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank
    @Schema(example = "krit")
    private String firstName;

    @NotBlank
    @Schema(example = "thanadka")
    private String lastName;
}
