package org.gtfo.spring_security_starter_project.dto.mapper;

import org.gtfo.spring_security_starter_project.dto.request.customer.CreateCustomerRequest;
import org.gtfo.spring_security_starter_project.dto.response.customer.CustomerResponse;
import org.gtfo.spring_security_starter_project.entity.Customer;

public class CustomerMapper {
    public static Customer createCustomerRequestToCustomer(CreateCustomerRequest request){
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getUserAccount().getId())
                .username(customer.getUserAccount().getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
