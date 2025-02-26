package com.example.kafkaproducerexample1.application.service;

import com.example.kafkaproducerexample1.application.mapping.CurrencyExchangeMapper;
import com.example.kafkaproducerexample1.application.model.CurrencyExchangeDto;
import com.example.kafkaproducerexample1.data.CurrencyExchangeRepository;
import com.example.kafkaproducerexample1.data.ExchangeRateRepository;
import com.example.kafkaproducerexample1.domain.model.CurrencyExchange;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    @Transactional
    public CurrencyExchange saveCurrencyExchange(CurrencyExchangeDto dto) {
        var currencyExchange = CurrencyExchangeMapper.INSTANCE.fromDto(dto);
        var exchangeRates = currencyExchange.getRates();

        if (exchangeRates == null || exchangeRates.isEmpty()) {
            throw new IllegalArgumentException("Exchange rates must not be null or empty");
        }

        exchangeRates.forEach(rate -> rate.setCurrencyExchange(currencyExchange));
        var savedExchangeRates = exchangeRateRepository.saveAll(exchangeRates);
        currencyExchange.setRates(savedExchangeRates);
        currencyExchange.setInsertedAt(OffsetDateTime.now());
        currencyExchange.setUpdatedAt(OffsetDateTime.now());

        return currencyExchangeRepository.save(currencyExchange);
    }
}
