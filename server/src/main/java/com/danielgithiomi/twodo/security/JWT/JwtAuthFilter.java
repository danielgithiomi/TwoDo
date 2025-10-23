package com.danielgithiomi.twodo.security.JWT;

import com.danielgithiomi.twodo.exceptions.JWTAuthenticationException;
import com.danielgithiomi.twodo.security.AuthUser;
import com.danielgithiomi.twodo.security.AuthUserDetailsService;
import com.danielgithiomi.twodo.security.interfaces.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extractJwtToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = authService.extractUsername(jwtToken);
        log.warn("User request: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (!authService.isJwtTokenValid(jwtToken))
                throw new JWTAuthenticationException("The JWT for user " + username + " has expired. Please login again");

            AuthUser userDetails = (AuthUser) authUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getPassword(), userDetails.getAuthorities()
            );
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // Add additional information to the request headers
            request.setAttribute("userId", userDetails.getUserId());
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) return null;
        return bearerToken.substring(7);
    }
}
