package com.payment.orchestration.controller;

import com.payment.orchestration.dto.LoginRequest;
import com.payment.orchestration.dto.LoginResponse;
import com.payment.orchestration.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        // temporary hardcoded credentials
        if(
                request.getUsername().equals("admin")
                        &&
                        request.getPassword().equals("admin123")
        ) {

            String token =
                    jwtService.generateToken(
                            request.getUsername()
                    );

            return new LoginResponse(token);
        }

        throw new RuntimeException(
                "Invalid username/password"
        );
    }
}