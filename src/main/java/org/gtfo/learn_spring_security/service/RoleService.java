package org.gtfo.learn_spring_security.service;

import org.gtfo.learn_spring_security.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}
