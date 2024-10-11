package org.gtfo.learn_spring_security.repository;

import org.gtfo.learn_spring_security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUserAccountId(String userAccountId);
}
