package com.danielgithiomi.twodo.security;

import com.danielgithiomi.twodo.security.interfaces.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${twodo.application.jwtConfig.JWTSecret}")
    private static String JWT_SECRET;

    @Override
    public UserDetails validateUser(String username, String password) {
        return null;
    }

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(createJwtClaims(userDetails))
                .compact();
    }

    @Override
    public boolean isJwtTokenValid(String authToken) {
        return false;
    }

    @Override
    public Map<String, Object> createJwtClaims(UserDetails userDetails) {
        return Map.of();
    }
}
