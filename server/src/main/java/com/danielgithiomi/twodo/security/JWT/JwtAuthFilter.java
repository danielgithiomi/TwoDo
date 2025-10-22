package com.danielgithiomi.twodo.security.JWT;

import com.danielgithiomi.twodo.exceptions.JWTExpirationException;
import com.danielgithiomi.twodo.security.AuthUserDetailsService;
import com.danielgithiomi.twodo.security.interfaces.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extractJwtToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = authService.extractUsername(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

            if (userDetails != null && authService.isJwtTokenValid(jwtToken))
                throw new JWTExpirationException("The JWT for user " + username + " has expired. Please login again");
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) return null;
        return bearerToken.substring(7);
    }
}
