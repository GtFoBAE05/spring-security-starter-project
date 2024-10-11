package org.gtfo.spring_security_starter_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.spring_security_starter_project.constant.RoleConstant;
import org.gtfo.spring_security_starter_project.dto.mapper.CustomerMapper;
import org.gtfo.spring_security_starter_project.dto.request.customer.CreateCustomerRequest;
import org.gtfo.spring_security_starter_project.dto.request.auth.RegisterRequest;
import org.gtfo.spring_security_starter_project.dto.response.customer.CustomerResponse;
import org.gtfo.spring_security_starter_project.entity.Customer;
import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.gtfo.spring_security_starter_project.repository.CustomerRepository;
import org.gtfo.spring_security_starter_project.service.CustomerService;
import org.gtfo.spring_security_starter_project.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserAccountService userAccountService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(RoleConstant.ROLE_CUSTOMER.getDescription())
                .build();
        UserAccount userAccount = userAccountService.createUser(registerRequest);

        Customer customer = CustomerMapper.createCustomerRequestToCustomer(request);
        customer.setUserAccount(userAccount);
        customerRepository.saveAndFlush(customer);

        return CustomerMapper.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getDetail(String userAccountId) {
        Customer customer = customerRepository.findByUserAccountId(userAccountId);
        return CustomerMapper.customerToCustomerResponse(customer);
    }
}
