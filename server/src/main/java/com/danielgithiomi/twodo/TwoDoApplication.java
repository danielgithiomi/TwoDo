package com.danielgithiomi.twodo;

import com.danielgithiomi.twodo.domains.records.ApplicationProperties;
import com.danielgithiomi.twodo.domains.records.JWTConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, JWTConfigProperties.class})
public class TwoDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoDoApplication.class, args);
    }

}
