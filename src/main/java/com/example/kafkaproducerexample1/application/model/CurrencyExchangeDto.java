package com.example.kafkaproducerexample1.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchangeDto {
    private boolean valid;
    private long updated;
    private String base;
    private Map<String, Double> rates;
}
