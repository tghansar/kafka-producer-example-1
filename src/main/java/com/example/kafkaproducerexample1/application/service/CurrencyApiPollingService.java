package com.example.kafkaproducerexample1.application.service;

import com.example.kafkaproducerexample1.application.client.CurrencyApiFeignClient;
import com.example.kafkaproducerexample1.application.client.CurrencyApiProperties;
import com.example.kafkaproducerexample1.application.kafka.KafkaProducer;
import com.example.kafkaproducerexample1.application.model.CurrencyExchangeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyApiPollingService {
    private final CurrencyApiFeignClient currencyApiFeignClient;
    private final CurrencyApiProperties currencyApiProperties;
    private final CurrencyExchangeService currencyExchangeService;
    private final KafkaProducer kafkaProducer;

//    @Scheduled(initialDelayString = "#{@currencyApiProperties.scheduledInitialDelay}", fixedDelayString = "#{@currencyApiProperties.scheduledFixedDelay}")
    @Scheduled(initialDelayString = "${currency-api.api.scheduled.initial-delay}", fixedDelayString = "${currency-api.api.scheduled.fixed-delay}")
    public void consumer() {
        try {
            CurrencyExchangeDto dto = currencyApiFeignClient.getCurrencyPayload(currencyApiProperties.getKey());
            if (dto == null) {
                throw new Exception("Currency API call returned null response");
            }
            log.info("Currency API call returned: {}", dto);

            var savedCurrencyExchange = currencyExchangeService.saveCurrencyExchange(dto);

            kafkaProducer.sendToKafka(savedCurrencyExchange);

        } catch (Exception e) {
            log.error("Failure in call to Currency API: {}", e.getMessage(), e);
        }
    }
}
