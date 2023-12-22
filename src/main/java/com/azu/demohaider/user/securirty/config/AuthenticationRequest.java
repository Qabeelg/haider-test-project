package com.azu.demohaider.user.securirty.config;

public record AuthenticationRequest(
        String username,

        String email,

        String password

) {
}