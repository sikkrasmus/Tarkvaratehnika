package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioHistoryDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.repository.PortfolioHistoryRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class PortfolioHistoryService {

    private final PortfolioHistoryRepository portfolioHistoryRepository;
    private final RequestService requestService;

    @Autowired
    public PortfolioHistoryService(PortfolioHistoryRepository portfolioHistoryRepository, RequestService requestService) {
        this.portfolioHistoryRepository = portfolioHistoryRepository;
        this.requestService = requestService;
    }

    public void createAndSaveValue(PortfolioHistoryDTO dto, Portfolio p) throws IOException, JSONException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        double value = requestService.getPriceForPortfolio(dto.getPortfolioID());
        PortfolioHistory history = new PortfolioHistory();
        history.setTime(time);
        history.setId(dto.getPortfolioID());
        history.setValue(value);
        history.setPortfolio(p);
        portfolioHistoryRepository.save(history);
    }

    public PortfolioHistory getHistoryByPortfolio(Portfolio portfolio) {
        return portfolioHistoryRepository.findByPortfolio(portfolio);
    }

    public void deleteAll() {
        portfolioHistoryRepository.deleteAll();
    }
}
