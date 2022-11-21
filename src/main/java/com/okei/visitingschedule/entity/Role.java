package com.okei.visitingschedule.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("Администратор"),
    USER_VISITOR("Посещающий"),
    USER_VISITED("Преподаватель");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
