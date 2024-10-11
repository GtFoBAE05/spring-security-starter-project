package org.gtfo.spring_security_starter_project.constant;

import lombok.Getter;

@Getter
public enum RoleConstant {
    ROLE_ADMIN("Admin"),
    ROLE_CASHIER("Kasir"),
    ROLE_CUSTOMER("Pelanggan");

    private final String description;

    RoleConstant(String description) {
        this.description = description;
    }

    public static RoleConstant findByDescription(String name){
        for (RoleConstant value : values()) {
            if(value.description.equalsIgnoreCase(name)){
                return value;
            }
        }

        return null;
    }
}
