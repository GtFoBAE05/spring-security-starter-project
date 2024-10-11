package org.gtfo.spring_security_starter_project.repository;

import org.gtfo.spring_security_starter_project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUserAccountId(String userAccountId);
}
