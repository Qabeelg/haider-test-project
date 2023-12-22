package com.azu.demohaider.user;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserRegistrationsRequest {

    private String username;
    private String email;
    private String password;
    private Integer age;
    private Set<RoleEnum> roleEnums;

    public UserRegistrationsRequest(String username, String email, String password, Integer age, Set<RoleEnum> roleEnums) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.roleEnums = roleEnums;
    }
}
