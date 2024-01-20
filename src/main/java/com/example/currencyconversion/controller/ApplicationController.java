package com.example.currencyconversion.controller;

import com.example.currencyconversion.config.CurrencyExchangeProxy;
import com.example.currencyconversion.dto.CurrencyConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    private Logger logger= LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        logger.info("## Calling currency-exchange service ###");
        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(from, to);
        BigDecimal totalCalculatedAmount = currencyConversion.getConversionMultiple().multiply(quantity);
        logger.info("totalCalculatedAmount:\t{}",totalCalculatedAmount);
        currencyConversion.setTotalCalculatedAmount(totalCalculatedAmount);
        currencyConversion.setQuantity(quantity);
        return currencyConversion;
    }
}
