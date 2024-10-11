package org.gtfo.learn_spring_security.service;

import org.gtfo.learn_spring_security.dto.request.customer.CreateCustomerRequest;
import org.gtfo.learn_spring_security.dto.response.customer.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
    CustomerResponse getDetail(String userAccountId);
}
