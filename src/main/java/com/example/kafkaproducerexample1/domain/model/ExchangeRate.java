package com.example.kafkaproducerexample1.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String currency;
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "currency_exchange_id")
    private CurrencyExchange currencyExchange;
}
