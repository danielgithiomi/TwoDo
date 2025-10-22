package com.danielgithiomi.twodo.security.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface AuthService {

    UserDetails validateUser(String username, String password);

    String generateJwtToken(UserDetails userDetails);

    boolean isJwtTokenValid(String authToken);

    Map<String, Object> createJwtClaims(UserDetails userDetails);

}
