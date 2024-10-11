package org.gtfo.spring_security_starter_project.service;

import org.gtfo.spring_security_starter_project.dto.request.customer.CreateCustomerRequest;
import org.gtfo.spring_security_starter_project.dto.response.customer.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
    CustomerResponse getDetail(String userAccountId);
}
