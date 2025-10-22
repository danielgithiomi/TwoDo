package com.danielgithiomi.twodo.security;

import com.danielgithiomi.twodo.security.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${twodo.application.JWTSecret}")
    private static String JWT_SECRET;

    @Override
    public UserDetails validateUser(String username, String password) {
        return null;
    }

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        return "";
    }

    @Override
    public boolean isJwtTokenValid(String authToken) {
        return false;
    }
}
