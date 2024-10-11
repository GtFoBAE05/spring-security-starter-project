package org.gtfo.spring_security_starter_project.repository;

import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByUsername(String name);
}
