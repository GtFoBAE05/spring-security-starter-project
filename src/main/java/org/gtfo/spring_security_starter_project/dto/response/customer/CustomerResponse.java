package org.gtfo.spring_security_starter_project.dto.response.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
