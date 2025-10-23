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

        log.info("🔍 JWT Filter processing: {} {}", request.getMethod(), request.getRequestURI());

        String jwtToken = extractJwtToken(request);
        if (jwtToken == null) {
            log.info("❌ No JWT token found in Authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("✅ JWT token found, extracting username...");
        String username = authService.extractUsername(jwtToken);
        log.info("👤 Username from JWT: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("🔐 Authenticating user: {}", username);

            if (authService.isJwtTokenValid(jwtToken)) {
                log.error("❌ JWT token is invalid or expired for user: {}", username);
                throw new JWTAuthenticationException("The JWT for user " + username + " has expired. Please login again");
            }

            log.info("✅ JWT token is valid, loading user details...");
            AuthUser userDetails = (AuthUser) authUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            log.info("✅ Authentication successful for user: {} with roles: {}", username, userDetails.getAuthorities());

            // Add additional information to the request attributes
            request.setAttribute("userId", userDetails.getUserId());
        } else if (username != null) {
            log.info("ℹ️ User already authenticated in SecurityContext");
        }

        log.info("➡️ Passing request to next filter");
        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) return null;
        return bearerToken.substring(7);
    }
}
