package org.gtfo.spring_security_starter_project.service;

import org.gtfo.spring_security_starter_project.dto.request.auth.RegisterRequest;
import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUser(RegisterRequest request);
    UserAccount getById(String id);
}
