package org.gtfo.spring_security_starter_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.gtfo.spring_security_starter_project.constant.Constant;

@Entity
@Table(name = Constant.CUSTOMER_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
}
