package com.example.kafkaproducerexample1.application.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "currency-api.api")
public class CurrencyApiProperties {
    private String baseUrl;
    private String endpoint;
    private String key;
    private String base;
    private long scheduledInitialDelay;
    private long scheduledFixedDelay;
}