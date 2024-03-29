package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.dto.request.LoginReq;
import com.jakub.taskmanagementapi.dto.request.RegisterReq;
import com.jakub.taskmanagementapi.dto.response.LoginRes;
import com.jakub.taskmanagementapi.dto.response.RegisterRes;
import com.jakub.taskmanagementapi.services.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> authenticateUser(
            @Valid @RequestBody LoginReq loginReq
    ) {
        String token = authenticationService.login(loginReq);

        return ResponseEntity.ok(new LoginRes(loginReq.getEmail(), token, "User logged in successfully!"));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRes> registerUser(
            @Valid @RequestBody RegisterReq registerReq
    ) {
        authenticationService.register(registerReq.toUser(), registerReq.getIsAdmin());

        return new ResponseEntity<>(new RegisterRes(registerReq.getEmail(), "User registered successfully!"), HttpStatus.CREATED);
    }
}
