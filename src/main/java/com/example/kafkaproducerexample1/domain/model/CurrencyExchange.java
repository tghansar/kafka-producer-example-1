package com.example.kafkaproducerexample1.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime insertedAt;

    private boolean valid;

    private String base;

    @OneToMany(mappedBy = "currencyExchange", cascade = CascadeType.ALL)
    private List<ExchangeRate> rates;
}
