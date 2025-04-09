package com.example.kafkaproducerexample1.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
