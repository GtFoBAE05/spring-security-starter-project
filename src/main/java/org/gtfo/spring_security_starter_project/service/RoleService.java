package org.gtfo.spring_security_starter_project.service;

import org.gtfo.spring_security_starter_project.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}
