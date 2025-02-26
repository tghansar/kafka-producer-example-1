package com.example.kafkaproducerexample1.application.mapping;

import com.example.kafkaproducerexample1.application.model.ExchangeRateDto;
import com.example.kafkaproducerexample1.domain.model.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = { OffsetDateTime.class })
public interface ExchangeRateMapper {

    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

    ExchangeRateDto toExchangeRateDto(ExchangeRate exchangeRate);

    ExchangeRate fromDto(ExchangeRateDto dto);

}
