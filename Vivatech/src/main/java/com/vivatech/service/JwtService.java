package com.vivatech.service;

public interface JwtService {

    String generateToken(String username);

    String validateToken(String token);
}

