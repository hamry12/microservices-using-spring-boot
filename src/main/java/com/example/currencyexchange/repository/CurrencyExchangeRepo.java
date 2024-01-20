package com.example.currencyexchange.repository;

import com.example.currencyexchange.dto.CurrencyExchange;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {


    CurrencyExchange findByFromAndTo(String from, String to);
}
