package com.example.authentication.spring.boots.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    @Schema(example = "krit.t || krit.t@extend-it-resource.com", description = "Username or Email ที่ใช้เข้าสู่ระบบ")
    private String username;

    @NotBlank
    @Schema(description = "รหัสผ่านที่มีอย่างน้อย 8 ตัว และประกอบด้วย A-Z, a-z, ตัวเลข, และอักขระพิเศษ", example = "P@ssw0rd")
    private String password;
}
