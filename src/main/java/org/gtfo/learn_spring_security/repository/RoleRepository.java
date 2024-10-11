package org.gtfo.learn_spring_security.repository;

import org.gtfo.learn_spring_security.constant.RoleConstant;
import org.gtfo.learn_spring_security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleConstant name);
}
