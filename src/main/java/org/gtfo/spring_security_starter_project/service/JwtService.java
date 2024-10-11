package org.gtfo.spring_security_starter_project.service;

import org.gtfo.spring_security_starter_project.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    String extractSubject(String token);
    String parseToken(String bearerToken);
}
