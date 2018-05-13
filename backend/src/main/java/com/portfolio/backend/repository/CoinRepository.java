package com.portfolio.backend.repository;

import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoinRepository extends CrudRepository<Coin, Long> {
    Coin findByShortname(String shortName);
    Coin findById(long Id);
    Coin deleteAllByPortfolioId(long Id);
    Coin findByPortfolioIdAndShortname(long Id, String shortName);

    List<Coin> findAllByPortfolioId(Long portfolioId);
}
