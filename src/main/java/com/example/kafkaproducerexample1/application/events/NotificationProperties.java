package com.example.kafkaproducerexample1.application.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka.topic.currencies")
public class NotificationProperties {
    boolean enableKafkaSend;
    String topicName;
}