package com.mycompany.auth.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateRequest {
    private String name;
    private String password;
    private String username;
    private String email;
    private String phone;
    private int age;
}
