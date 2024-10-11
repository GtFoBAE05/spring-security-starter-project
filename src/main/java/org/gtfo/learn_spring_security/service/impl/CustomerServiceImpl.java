package org.gtfo.learn_spring_security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.learn_spring_security.constant.RoleConstant;
import org.gtfo.learn_spring_security.dto.mapper.CustomerMapper;
import org.gtfo.learn_spring_security.dto.request.customer.CreateCustomerRequest;
import org.gtfo.learn_spring_security.dto.request.auth.RegisterRequest;
import org.gtfo.learn_spring_security.dto.response.customer.CustomerResponse;
import org.gtfo.learn_spring_security.entity.Customer;
import org.gtfo.learn_spring_security.entity.UserAccount;
import org.gtfo.learn_spring_security.repository.CustomerRepository;
import org.gtfo.learn_spring_security.service.CustomerService;
import org.gtfo.learn_spring_security.service.UserAccountService;
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
