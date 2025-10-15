package com.danielgithiomi.twodo.config;

import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${twodo.application.schema}")
    String databaseSchema;

    @Bean
    @ConditionalOnProperty(prefix = "twodo", value = "application.manualDBPopulationEnabled", havingValue = "true")
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // Populate database with default data
            User user = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .username("admin".toUpperCase())
                    .email("admin@twodo.com")
                    .password(passwordEncoder.encode("Admin123!"))
                    .build();

            userRepository.save(user);


            log.info("Database schema for the {} application: {}", applicationName, databaseSchema);
        };
    }

}
