package com.example.kafkaproducerexample1.application.client;

import com.example.kafkaproducerexample1.application.model.CurrencyExchangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* See https://currencyapi.net/documentation
* */
@FeignClient(name = "currencyApiFeignClient", url = "${currency-api.api.base.url}")
public interface CurrencyApiFeignClient {

    @GetMapping(value = "${currency-api.api.endpoint}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CurrencyExchangeDto getCurrencyPayload(@RequestParam(value = "key") String key);
}
