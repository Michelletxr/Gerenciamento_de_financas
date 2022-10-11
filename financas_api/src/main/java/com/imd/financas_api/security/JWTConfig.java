package com.imd.financas_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JWTConfig {

    public String passwordEnconde(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
