package com.danielgithiomi.twodo.security.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface AuthService {

    UserDetails validateUser(String username, String password);

    String generateJwtToken(UserDetails userDetails);

    boolean isJwtTokenValid(String jwtToken);

    boolean isJwtTokenExpired(String jwtToken);

    String extractUsername(String jwtToken);

    List<SimpleGrantedAuthority> extractRoles(String jwtToken);

    Date extractExpirationDate(String jwtToken);

    Claims extractAllClaims(String jwtToken);

    <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver);

    Map<String, Object> createJwtClaims(UserDetails userDetails);

}
