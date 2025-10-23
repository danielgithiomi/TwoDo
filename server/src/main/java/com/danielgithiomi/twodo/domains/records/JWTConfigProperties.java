package com.danielgithiomi.twodo.domains.records;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twodo.application.jwt-config")
public record JWTConfigProperties(
        String JWTSecret,
        int JWTValidityDurationInHrs,
        boolean generateJwtSecretEnabled
) {
}
