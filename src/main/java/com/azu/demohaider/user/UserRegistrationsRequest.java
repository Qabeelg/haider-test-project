package com.azu.demohaider.user;

import lombok.Data;

@Data
public class UserRegistrationsRequest {

    private String username;
    private String email;
    private String password;
    private Integer age;

    public UserRegistrationsRequest(String username, String email, String password, Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
