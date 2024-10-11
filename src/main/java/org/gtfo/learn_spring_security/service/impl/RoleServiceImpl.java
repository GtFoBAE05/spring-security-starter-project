package org.gtfo.learn_spring_security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.learn_spring_security.constant.RoleConstant;
import org.gtfo.learn_spring_security.entity.Role;
import org.gtfo.learn_spring_security.repository.RoleRepository;
import org.gtfo.learn_spring_security.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(RoleConstant.findByDescription(name)).orElseThrow(
                () -> {
                    log.error("Role with name {} not found", name);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
                }
        );
    }
}
