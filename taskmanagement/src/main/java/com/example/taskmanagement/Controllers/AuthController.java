package com.example.taskmanagement.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.DTO.LoginRequest;
import com.example.taskmanagement.DTO.RegisterRequest;
import com.example.taskmanagement.DTO.VerifyOTP;
import com.example.taskmanagement.ServiceLayers.AuthService;


@RestController
@RequestMapping("/api")
public class AuthController {

    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp( @RequestBody VerifyOTP request) {
        return ResponseEntity.ok(authService.verifyOtp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
