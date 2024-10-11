package org.gtfo.learn_spring_security.service;

import org.gtfo.learn_spring_security.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    String extractSubject(String token);
    String parseToken(String bearerToken);
}
