package org.gtfo.spring_security_starter_project.repository;

import org.gtfo.spring_security_starter_project.constant.RoleConstant;
import org.gtfo.spring_security_starter_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleConstant name);
}
