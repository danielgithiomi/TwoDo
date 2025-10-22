package com.danielgithiomi.twodo.config;

import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.repositories.RoleRepository;
import com.danielgithiomi.twodo.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.danielgithiomi.twodo.domains.enums.UserRoles.ADMIN;
import static com.danielgithiomi.twodo.domains.enums.UserRoles.USER;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${twodo.application.schema}")
    String databaseSchema;

    @Bean
    @ConditionalOnProperty(prefix = "twodo.application", value = "manualDBPopulationEnabled", havingValue = "true")
    CommandLineRunner commandLineRunner(
            UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
            @Value("${twodo.application.generateJwtSecretEnabled}") boolean generateJwtSecretEnabled

    ) {
        return args -> {

            // Generate a secret key for JWT authentication
            if (generateJwtSecretEnabled) {
                String JWTSecret = generateJwtSecret();
                log.info("Generated JWT secret: {}", JWTSecret);
            } else {
                log.warn("JWT secret generation is disabled");
            }

            // Populate the database with default data
            // Application Roles
            Role user_role = Role.builder().role(USER).build();
            Role admin_role = Role.builder().role(ADMIN).build();

            roleRepository.saveAll(List.of(user_role, admin_role));

            // Admin User
            Set<Role> adminRoles = new HashSet<Role>();
            adminRoles.add(admin_role);
            adminRoles.add(user_role);

            User user = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .username("admin".toUpperCase())
                    .roles(adminRoles)
                    .email("admin@twodo.com")
                    .password(passwordEncoder.encode("Admin123!"))
                    .build();

            userRepository.save(user);

            log.info("Database schema populated for the {} application: {}", applicationName, databaseSchema);
        };
    }

    private String generateJwtSecret() {
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
