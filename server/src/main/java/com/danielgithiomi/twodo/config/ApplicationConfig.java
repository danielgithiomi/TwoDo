package com.danielgithiomi.twodo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${twodo.application.schema}")
    String databaseSchema;

    @Bean
    @ConditionalOnProperty(prefix = "twodo", value = "application.manualDBPopulationEnabled", havingValue = "true")
    CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("Database schema for the {} application: {}", applicationName, databaseSchema);
        };
    }

}
