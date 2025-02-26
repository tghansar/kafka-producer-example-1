package com.example.kafkaproducerexample1.application.mapping;

import com.example.kafkaproducerexample1.application.kafka.CurrencyExchangeNotification;
import com.example.kafkaproducerexample1.application.model.CurrencyExchangeDto;
import com.example.kafkaproducerexample1.domain.model.CurrencyExchange;
import com.example.kafkaproducerexample1.domain.model.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = { OffsetDateTime.class })
public interface CurrencyExchangeMapper {
    CurrencyExchangeMapper INSTANCE = Mappers.getMapper(CurrencyExchangeMapper.class);

    CurrencyExchangeDto toDto(CurrencyExchange currencyExchange);

    CurrencyExchange fromDto(CurrencyExchangeDto dto);

    CurrencyExchangeNotification toKafkaNotification(CurrencyExchange currencyExchange);

    default List<ExchangeRate> map(Map<String, Double> rates) {
        return rates.entrySet().stream()
                .map(entry -> ExchangeRate.builder()
                                .currency(entry.getKey())
                                .rate(entry.getValue())
                                .build())
                .collect(Collectors.toList());
    }

    default Map<String, Double> map(List<ExchangeRate> rates) {
        return rates.stream()
                .collect(Collectors.toMap(ExchangeRate::getCurrency, ExchangeRate::getRate));
    }
}
