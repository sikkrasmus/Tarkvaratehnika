package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.user.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void createAndSavePortfolio(PortfolioDTO portfolioDTO, HttpSession session) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setDescription(portfolioDTO.getDescription());
        portfolio.setUsername(session.getAttribute("name").toString());
        System.out.println("PortfolioUsername: " + portfolio.getUsername());
        System.out.println("PortfolioName: " + portfolio.getName());
        portfolioRepository.save(portfolio);
    }

    public List<String> getAllUserPortfolios(String username) {
        List<String> portfolios = new ArrayList<>();
        Iterable<Portfolio> allPortfolios = portfolioRepository.findAll();

        for (Portfolio p : allPortfolios) {
           if (p.getUsername().equals(username)) {
               portfolios.add(p.getName());
           }
        }
        return portfolios;
    }

}
