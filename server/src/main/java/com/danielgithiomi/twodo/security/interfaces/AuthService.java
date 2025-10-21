package com.danielgithiomi.twodo.security.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails validateUser(String username, String password);

    String generateJwtToken(UserDetails userDetails);

    boolean isJwtTokenValid(String authToken);

}
