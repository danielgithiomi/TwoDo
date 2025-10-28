package com.danielgithiomi.twodo.security;

import com.danielgithiomi.twodo.exceptions.JwtAuthenticationException;
import com.danielgithiomi.twodo.exceptions.ValidateUserException;
import com.danielgithiomi.twodo.security.interfaces.AuthService;
import com.danielgithiomi.twodo.utils.HelperFunctions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private final AuthUserDetailsService authUserDetailsService;

    @Value("${twodo.application.name}")
    private String JWT_ISSUER;

    @Value("${twodo.application.jwtConfig.JWTSecret}")
    private String JWT_SECRET;

    @Value("${twodo.application.jwtConfig.JWTValidityDurationInHrs}")
    private int JWT_VALIDITY_DURATION_IN_HRS;

    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails validateUser(String username, String password) {
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        if (authenticate == null || !authenticate.isAuthenticated())
            throw new ValidateUserException("Invalid username or password. Please check and try again");

        return userDetails;
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
    public boolean isJwtTokenValid(String jwtToken) {
        extractAllClaims(jwtToken);
        return !isJwtTokenExpired(jwtToken);
    }

    @Override
    public boolean isJwtTokenExpired(String jwtToken) {
        Date jwtExpirationDate = extractExpirationDate(jwtToken);
        return jwtExpirationDate != null && Date.from(Instant.now()).after(jwtExpirationDate);
    }

    @Override
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public Date extractExpirationDate(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    @Override
    public Claims extractAllClaims(String jwtToken) {
        try {
            return Jwts.parser()
                    .verifyWith(generateSignWithKey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("The JWT passed in is expired.", e.getMessage());
        } catch (JwtException e) {
            throw new JwtAuthenticationException("The JWT passed in is invalid", e.getMessage());
        } catch (Exception e) {
            throw new JwtAuthenticationException("An error occurred while parsing the JWT", e.getMessage());
        }
    }

    @Override
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    @Override
    public List<SimpleGrantedAuthority> extractRoles(String jwtToken) {
        List<?> roles = extractClaim(jwtToken, claims -> claims.get("roles", List.class));
        return roles
                .stream()
                .map(Object::toString)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Map<String, Object> createJwtClaims(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        return claims;
    }

    private SecretKey generateSignWithKey() {
        byte[] keyBytes = Base64.getDecoder().decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
