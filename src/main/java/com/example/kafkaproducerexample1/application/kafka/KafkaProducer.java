package com.example.kafkaproducerexample1.application.kafka;

import com.example.kafkaproducerexample1.application.events.NotificationProperties;
import com.example.kafkaproducerexample1.application.mapping.CurrencyExchangeMapper;
import com.example.kafkaproducerexample1.domain.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, CurrencyExchangeNotification> kafkaTemplate;
    private final NotificationProperties properties;

    public void sendToKafka(CurrencyExchange currencyExchange) {
        try {
            String currentTime = OffsetDateTime.now().toString();
            CurrencyExchangeNotification notification =
                    CurrencyExchangeMapper.INSTANCE.toKafkaNotification(currencyExchange);
            notification.setTimestamp(currentTime);

            Message<CurrencyExchangeNotification> message = MessageBuilder
                    .withPayload(notification)
                    .setHeader(KafkaHeaders.TOPIC, properties.getTopicName())
                    .setHeader(KafkaHeaders.KEY, currentTime)
                    .build();

            log.info("sending kafka message with payload: {}", notification);
            kafkaTemplate.send(message);
            log.info("kafka message sent successfully");

        } catch (Exception e) {
            log.error("failed to send a kafka message: {}", e.getMessage(), e);
        }
    }
}
