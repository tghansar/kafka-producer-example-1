package com.example.kafkaproducerexample1.application.kafka;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@Builder
public class CurrencyExchangeNotification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String timestamp;
    private List<ExchangeRateNotification> rates;
}