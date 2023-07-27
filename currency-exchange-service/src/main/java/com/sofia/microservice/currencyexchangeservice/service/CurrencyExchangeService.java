package com.sofia.microservice.currencyexchangeservice.service;


import com.sofia.microservice.currencyexchangeservice.model.CurrencyExchange;
import com.sofia.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository repository;

     public CurrencyExchange convertFromTo(String from, String to) {
           return repository.findByFromAndTo(from, to);
     }
}
