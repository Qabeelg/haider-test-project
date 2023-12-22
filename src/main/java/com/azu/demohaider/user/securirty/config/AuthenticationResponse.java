package com.azu.demohaider.user.securirty.config;

import com.azu.demohaider.user.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Set;

@Builder
public record AuthenticationResponse(
        String username,
        String email,
        @JsonProperty("access_token")
        String token,
        Set<RoleEnum> role
) {
}
