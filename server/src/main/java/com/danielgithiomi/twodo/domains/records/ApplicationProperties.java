package com.danielgithiomi.twodo.domains.records;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twodo.application")
public record ApplicationProperties(
        String name,
        String version,
        String schema,
        String developer,
        String description,
        String repository,
        String JWTSecret,
        Boolean generateJwtSecretEnabled,
        Boolean manualDBPopulationEnabled

) {
}
