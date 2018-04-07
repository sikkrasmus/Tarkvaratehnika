package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioHistoryDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.repository.PortfolioHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PortfolioHistoryService {

    private final PortfolioHistoryRepository portfolioHistoryRepository;

    @Autowired
    public PortfolioHistoryService(PortfolioHistoryRepository portfolioHistoryRepository) {
        this.portfolioHistoryRepository = portfolioHistoryRepository;
    }

    public void createAndSaveValue(PortfolioHistoryDTO dto, Portfolio p) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        double value = RequestService.getPriceForPortfolio(dto.getPortfolioID());
        PortfolioHistory history = new PortfolioHistory();
        history.setTime(time);
        history.setId(dto.getPortfolioID());
        history.setValue(value);
        history.setPortfolio(p);
        portfolioHistoryRepository.save(history);
    }
}
