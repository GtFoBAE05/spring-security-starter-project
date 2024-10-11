package org.gtfo.learn_spring_security.service;

import org.gtfo.learn_spring_security.dto.request.auth.RegisterRequest;
import org.gtfo.learn_spring_security.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUser(RegisterRequest request);
    UserAccount getById(String id);
}
