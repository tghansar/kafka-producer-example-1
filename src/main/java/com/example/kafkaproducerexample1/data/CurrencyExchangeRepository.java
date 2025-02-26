package com.example.kafkaproducerexample1.data;

import com.example.kafkaproducerexample1.domain.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, UUID> {
}
