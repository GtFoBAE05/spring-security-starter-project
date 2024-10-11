package org.gtfo.spring_security_starter_project.controller;

import lombok.RequiredArgsConstructor;
import org.gtfo.spring_security_starter_project.dto.mapper.BasicResponseMapper;
import org.gtfo.spring_security_starter_project.dto.request.customer.CreateCustomerRequest;
import org.gtfo.spring_security_starter_project.dto.response.customer.CustomerResponse;
import org.gtfo.spring_security_starter_project.entity.UserAccount;
import org.gtfo.spring_security_starter_project.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    private ResponseEntity<?> createCustomer(
            @RequestBody CreateCustomerRequest customerRequest
    ) {
        CustomerResponse customer = customerService.createCustomer(customerRequest);
        return BasicResponseMapper.toBasicResponse(
                HttpStatus.CREATED,
                "Succesfully create customer",
                customer
        );
    }

    @GetMapping
    private ResponseEntity<?> getMyDetail() {
        UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BasicResponseMapper.toBasicResponse(
                HttpStatus.CREATED,
                "Succesfully fetch customer detail",
                customerService.getDetail(userAccount.getId())
        );
    }
}
