package org.gtfo.learn_spring_security.service;

import org.gtfo.learn_spring_security.dto.request.auth.LoginRequest;
import org.gtfo.learn_spring_security.dto.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
