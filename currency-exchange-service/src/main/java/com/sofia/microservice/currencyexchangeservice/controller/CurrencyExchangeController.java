package com.sofia.microservice.currencyexchangeservice.controller;

import com.sofia.microservice.currencyexchangeservice.model.CurrencyExchange;
import com.sofia.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.sofia.microservice.currencyexchangeservice.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {

//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L,
//                from,
//                to,
//                BigDecimal.valueOf(50));

        CurrencyExchange currencyExchange
                = currencyExchangeService.convertFromTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from +" to " + to);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;

    }
}
