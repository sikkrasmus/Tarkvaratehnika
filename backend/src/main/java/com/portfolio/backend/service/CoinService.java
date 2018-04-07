package com.portfolio.backend.service;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    private final CoinRepository coinRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }


    public void createAndSaveCoin(CoinDTO coinDTO, Portfolio p) {
        Coin coin = new Coin();
        coin.setExchange(coinDTO.getExchange());
        coin.setLongname(coinDTO.getLongName());
        coin.setShortname(coinDTO.getShortName());
        coin.setPortfolio(p);
        coin.setAmount(coinDTO.getAmount());
        coin.setTimeadded(coinDTO.getTimeAdded());
        coin.setPricebought(coinDTO.getPriceBought());
        coinRepository.save(coin);
    }



}
