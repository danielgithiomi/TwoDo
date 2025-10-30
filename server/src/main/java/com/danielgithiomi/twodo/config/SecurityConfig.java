package com.danielgithiomi.twodo.config;

import com.danielgithiomi.twodo.security.JWT.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable)) // To allow H2 Console to use IFrames
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/error", "/h2/**", "/api/v1/test/**", "/api/v1/genders").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.applyPermitDefaultValues();
//        corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfig.setAllowedHeaders(List.of("*", "Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With"));

        UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();
        urlSource.registerCorsConfiguration("/**", corsConfig);
        return urlSource;
    }
}
