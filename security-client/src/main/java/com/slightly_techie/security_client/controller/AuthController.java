package com.slightly_techie.security_client.controller;


import com.slightly_techie.security_client.dto.AuthRequest;
import com.slightly_techie.security_client.dto.AuthResponse;
import com.slightly_techie.security_client.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
