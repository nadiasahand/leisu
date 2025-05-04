package com.mycompany.database.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    ;

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
