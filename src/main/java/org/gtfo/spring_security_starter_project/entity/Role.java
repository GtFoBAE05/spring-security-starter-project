package org.gtfo.spring_security_starter_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.gtfo.spring_security_starter_project.constant.Constant;
import org.gtfo.spring_security_starter_project.constant.RoleConstant;

@Entity
@Table(name = Constant.ROLES_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, unique = true)
    private RoleConstant name;


}
