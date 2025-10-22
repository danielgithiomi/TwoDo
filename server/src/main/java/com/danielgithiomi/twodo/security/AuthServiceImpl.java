package com.danielgithiomi.twodo.security;

import com.danielgithiomi.twodo.security.interfaces.AuthService;
import com.danielgithiomi.twodo.utils.HelperFunctions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${twodo.application.name}")
    private static String JWT_ISSUER;

    @Value("${twodo.application.jwtConfig.JWTSecret}")
    private static String JWT_SECRET;

    @Value("${twodo.application.jwtConfig.JWTValidityDurationInHrs}")
    private static int JWT_VALIDITY_DURATION_IN_HRS;

    @Override
    public UserDetails validateUser(String username, String password) {
        return null;
    }

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        Long jwtDuration = HelperFunctions.hrsToMillis(JWT_VALIDITY_DURATION_IN_HRS);

        return Jwts.builder()
                .issuer(JWT_ISSUER)
                .issuedAt(Date.from(Instant.now()))
                .subject(userDetails.getUsername())
                .claims(createJwtClaims(userDetails))
                .expiration(Date.from(Instant.now().plusMillis(jwtDuration)))
                .signWith(generateSignWithKey())
                .compact();
    }

    @Override
    public boolean isJwtTokenValid(String authToken) {
        return false;
    }

    @Override
    public Map<String, Object> createJwtClaims(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities());

        return claims;
    }

    private SecretKey generateSignWithKey() {
        byte[] keyBytes = Base64.getDecoder().decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
