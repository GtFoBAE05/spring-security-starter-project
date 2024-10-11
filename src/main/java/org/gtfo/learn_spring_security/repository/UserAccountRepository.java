package org.gtfo.learn_spring_security.repository;

import org.gtfo.learn_spring_security.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByUsername(String name);
}
