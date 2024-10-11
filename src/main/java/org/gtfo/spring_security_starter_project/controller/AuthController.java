package org.gtfo.spring_security_starter_project.controller;

import lombok.RequiredArgsConstructor;
import org.gtfo.spring_security_starter_project.dto.mapper.BasicResponseMapper;
import org.gtfo.spring_security_starter_project.dto.request.auth.LoginRequest;
import org.gtfo.spring_security_starter_project.dto.response.auth.LoginResponse;
import org.gtfo.spring_security_starter_project.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return BasicResponseMapper.toBasicResponse(HttpStatus.OK, "Successfully login", loginResponse);
    }

}
