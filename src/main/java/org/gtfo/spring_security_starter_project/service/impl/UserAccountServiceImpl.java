package org.gtfo.spring_security_starter_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.spring_security_starter_project.dto.request.auth.RegisterRequest;
import org.gtfo.spring_security_starter_project.entity.Role;
import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.gtfo.spring_security_starter_project.repository.UserAccountRepository;
import org.gtfo.spring_security_starter_project.service.RoleService;
import org.gtfo.spring_security_starter_project.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService{
    private final RoleService roleService;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount createUser(RegisterRequest request) {
        Role role = roleService.getRoleByName(request.getRole());

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(request.getUsername());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount.setRole(Set.of(role));
        userAccountRepository.saveAndFlush(userAccount);

        return userAccount;
    }

    @Override
    public UserAccount getById(String id) {
        return userAccountRepository.findById(id).orElseThrow(
                () -> {
                    log.error("User with id {} not found", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                }
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username).orElseThrow(
                () -> {
                    log.error("User with username {} not found", username);
                    return new UsernameNotFoundException("Invalid Credential");
                }
        );
    }
}
