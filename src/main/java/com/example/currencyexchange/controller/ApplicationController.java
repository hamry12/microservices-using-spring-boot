package com.example.currencyexchange.controller;

import com.example.currencyexchange.dto.CurrencyExchange;
import com.example.currencyexchange.repository.CurrencyExchangeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private Logger logger= LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to){

        CurrencyExchange currencyExchange= currencyExchangeRepo.findByFromAndTo(from, to);
        if (currencyExchange == null){
            throw new RuntimeException("Unable to Find Record");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;

    }

}
