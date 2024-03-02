package com.jakub.taskmanagementapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("Current Profile: Dev");
        return "Current Profile: Dev";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        System.out.println("Current Profile: Prod");
        return "Current Profile: Prod";
    }
}