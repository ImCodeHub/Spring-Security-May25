package com.example.Spring_Security_May25.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role {
    ADMIN,
    EMPLOYEE,
    MANAGER,
    HR;

    public List<SimpleGrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.name())); //ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_HR
    }

}
