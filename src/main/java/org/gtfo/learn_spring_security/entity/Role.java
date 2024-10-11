package org.gtfo.learn_spring_security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.gtfo.learn_spring_security.constant.Constant;
import org.gtfo.learn_spring_security.constant.RoleConstant;

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
