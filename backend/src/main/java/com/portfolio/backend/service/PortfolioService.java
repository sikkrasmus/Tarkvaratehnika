package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.user.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void createAndSavePortfolio(PortfolioDTO portfolioDTO, HttpSession session){
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setDescription(portfolioDTO.getDescription());
        portfolio.setUsername(session.getAttribute("name").toString());
        portfolioRepository.save(portfolio);
    }
}
