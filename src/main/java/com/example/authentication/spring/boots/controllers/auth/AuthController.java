package com.example.authentication.spring.boots.controllers.auth;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.spring.boots.dtos.auth.ForgetPasswordRequest;
import com.example.authentication.spring.boots.dtos.auth.LoginRequest;
import com.example.authentication.spring.boots.dtos.auth.PaginateRequest;
import com.example.authentication.spring.boots.dtos.auth.RegisterRequest;
import com.example.authentication.spring.boots.dtos.auth.UpdateUserRequest;
import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.services.SendNotification.GmailSenderService;
import com.example.authentication.spring.boots.services.auth.ForgetPasswordService;
import com.example.authentication.spring.boots.services.auth.ListUsersService;
import com.example.authentication.spring.boots.services.auth.LoginService;
import com.example.authentication.spring.boots.services.auth.PaginateUsersService;
import com.example.authentication.spring.boots.services.auth.RegisterService;
import com.example.authentication.spring.boots.services.auth.UpdateUserService;
import com.example.authentication.spring.boots.services.auth.UserDetailService;
import com.example.authentication.spring.boots.services.mapResponse.response.PaginatedResponse;
import com.example.authentication.spring.boots.services.mapResponse.response.UserSimpleResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication APIs")
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final ListUsersService listUsersService;
    private final PaginateUsersService paginateUsersService;
    private final UserDetailService userDetailService;
    private final UpdateUserService updateUserService;
    private final ForgetPasswordService forgetPasswordService;
    private final GmailSenderService gmailSenderService;

    public AuthController(RegisterService registerService, LoginService loginService,
            ListUsersService listUsersService, PaginateUsersService paginateUsersService,
            UserDetailService userDetailService, UpdateUserService updateUserService,
            ForgetPasswordService forgetPasswordService, GmailSenderService gmailSenderService) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.listUsersService = listUsersService;
        this.paginateUsersService = paginateUsersService;
        this.userDetailService = userDetailService;
        this.updateUserService = updateUserService;
        this.forgetPasswordService = forgetPasswordService;
        this.gmailSenderService = gmailSenderService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User result = registerService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        User user = loginService.login(request);
        return ResponseEntity.ok(user); // หรือ return token ก็ได้
    }

    @GetMapping()
    public ResponseEntity<List<UserSimpleResponse>> listSimpleUsers() {
        return ResponseEntity.ok(listUsersService.listUser());
    }

    @GetMapping("/paginate")
    public ResponseEntity<PaginatedResponse<User>> listUsers(PaginateRequest pageable) {
        PaginatedResponse<User> response = paginateUsersService.execute(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> detailUser(@RequestParam String param) {
        return ResponseEntity.ok(userDetailService.execute(param));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestParam String id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(updateUserService.execute(id, request));
    }

    @PostMapping("/forget-password")
    public String forgetPassword(@RequestBody ForgetPasswordRequest request) {
        return forgetPasswordService.execute(request);
    }

    @PostMapping("/reset-password")
    public Boolean resetPassword(@RequestBody ForgetPasswordRequest request) {
        return gmailSenderService.sendResetPasswordEmail(request.getEmail());
    }

    @Operation(summary = "test view response")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/spring-version")
    public String springVersion() {
        return org.springframework.web.method.ControllerAdviceBean.class
                .getProtectionDomain().getCodeSource().getLocation().toString();
    }

}
