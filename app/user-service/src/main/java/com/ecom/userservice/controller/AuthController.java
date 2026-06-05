package com.ecom.userservice.controller;

import com.ecom.userservice.dto.ApiResponse;
import com.ecom.userservice.dto.AuthenticationRequest;
import com.ecom.userservice.dto.AuthenticationResponse;
import com.ecom.userservice.dto.RefreshTokenRequest;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody UserDto userDto) {
        String message = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("SUCCESS", message, null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@Valid @RequestBody AuthenticationRequest authRequest) {
        AuthenticationResponse response = userService.authenticate(authRequest);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Authentication successful", response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        AuthenticationResponse response = userService.refreshToken(request);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Token refreshed", response));
    }
}
