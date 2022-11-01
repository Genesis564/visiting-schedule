package com.okei.visitingschedule.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER_VISITOR,
    USER_VISITED;

    @Override
    public String getAuthority() {
        return name();
    }
}
