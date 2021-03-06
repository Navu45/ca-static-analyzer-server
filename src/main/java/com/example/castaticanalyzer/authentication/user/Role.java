package com.example.castaticanalyzer.authentication.user;

import org.springframework.security.core.GrantedAuthority;

/** @DomainEntity */

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
