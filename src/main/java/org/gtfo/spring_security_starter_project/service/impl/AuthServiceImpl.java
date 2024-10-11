package org.gtfo.spring_security_starter_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.spring_security_starter_project.dto.mapper.UserAccountMapper;
import org.gtfo.spring_security_starter_project.dto.request.auth.LoginRequest;
import org.gtfo.spring_security_starter_project.dto.response.auth.LoginResponse;
import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.gtfo.spring_security_starter_project.service.AuthService;
import org.gtfo.spring_security_starter_project.service.JwtService;
import org.gtfo.spring_security_starter_project.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserAccount userAccount = (UserAccount) userAccountService.loadUserByUsername(loginRequest.getUsername());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userAccount.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credential");
        }

        return UserAccountMapper.userAccountToLoginResponse(jwtService.generateToken(userAccount));
    }
}
