package com.imd.financas_api.security;

public class SecurityConstants {
    public static final String SECRET = "e25485f8-48c7-45ae-9312-f785e85d7727";
    public static final long EXPIRATION_TIME = 3600_000; // 1 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
