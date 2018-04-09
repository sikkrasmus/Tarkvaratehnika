package com.portfolio.backend.repository;

import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.PortfolioHistory;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioHistoryRepository extends CrudRepository<PortfolioHistory, Long> {
    PortfolioHistory findByPortfolio(Portfolio portfolio);
}

