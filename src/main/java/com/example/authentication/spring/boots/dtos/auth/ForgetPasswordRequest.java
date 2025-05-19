package com.example.authentication.spring.boots.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ForgetPasswordRequest {
    @Schema(example = "krit.t@extend-it-resource.com", description = "สำหรับการส่งข้อมูล")
    @Email
    private String email;
}
