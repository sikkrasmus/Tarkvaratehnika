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
    private final PortfolioService portfolioService;
    private final CoinNamesService coinNamesService;

    @Autowired
    public CoinService(CoinRepository coinRepository, CoinNamesRepository coinNamesRepository, PortfolioService portfolioService, CoinNamesService coinNamesService) {
        this.coinRepository = coinRepository;
        this.coinNamesRepository = coinNamesRepository;
        this.portfolioService = portfolioService;
        this.coinNamesService = coinNamesService;
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

    public String getCoinShortName(String longname){
        CoinNames coinNames = coinNamesRepository.findByLongname(longname);
        return coinNames.getShortname();
    }

    public Coin getCoinBy(Long id) {
        return coinRepository.findById(id);
    }

    public void deleteAll() {
        coinRepository.deleteAll();
    }

    public List<CoinNames> getAllCoins() {
        return (List<CoinNames>) coinNamesRepository.findAll();
    }

    public void sellCoin(CoinDTO coinDTO) {
        Coin coin = new Coin();
        coin.setAmount(-coinDTO.getAmount());
        coin.setPortfolio(portfolioService.getPortfolioById(coinDTO.getPortfolioId()));
        coin.setPricebought(coinDTO.getPriceBought());
        coin.setShortname(coinDTO.getShortName());
        coin.setLongname(coinNamesService.getCoinNamesBy(coinDTO.getShortName()).getLongname());
        coinRepository.save(coin);
    }

    public void buyCoin(CoinDTO coinDTO) {
        Coin coin = new Coin();
        coin.setAmount(coinDTO.getAmount());
        coin.setPortfolio(portfolioService.getPortfolioById(coinDTO.getPortfolioId()));
        coin.setPricebought(coinDTO.getPriceBought());
        coin.setShortname(coinDTO.getShortName());
        coin.setLongname(coinNamesService.getCoinNamesBy(coinDTO.getShortName()).getLongname());
        coinRepository.save(coin);
    }
}
