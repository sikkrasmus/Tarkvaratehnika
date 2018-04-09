package com.portfolio.backend.service;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.CoinNamesRepository;
import com.portfolio.backend.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CoinService {

    private final CoinRepository coinRepository;
    private final CoinNamesRepository coinNamesRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository, CoinNamesRepository coinNamesRepository) {
        this.coinRepository = coinRepository;
        this.coinNamesRepository = coinNamesRepository;
    }


    public Coin createAndSaveCoin(CoinDTO coinDTO, Portfolio p) {
        Coin coin = new Coin();
        coin.setExchange(coinDTO.getExchange());
        coin.setLongname(coinDTO.getLongName());
        coin.setShortname(getCoinShortName(coinDTO.getLongName()));
        coin.setPortfolio(p);
        coin.setAmount(coinDTO.getAmount());
        coin.setTimeadded(new Timestamp(System.currentTimeMillis()));
        coin.setPricebought(coinDTO.getPriceBought());
        coinRepository.save(coin);
        return coin;
    }

    private String getCoinShortName(String longname){
        System.out.println(longname);
        CoinNames coinNames = coinNamesRepository.findByLongname(longname);
        return coinNames.getShortname();
    }

    public List<CoinNames> getAllCoins(){
        return (List<CoinNames>) coinNamesRepository.findAll();
    }

}
