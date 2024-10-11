package org.gtfo.spring_security_starter_project.service;

import org.gtfo.spring_security_starter_project.dto.request.auth.LoginRequest;
import org.gtfo.spring_security_starter_project.dto.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
