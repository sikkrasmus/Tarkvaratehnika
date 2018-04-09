package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.repository.PortfolioHistoryRepository;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final PortfolioHistoryRepository portfolioHistoryRepository;
    private final RequestService requestService;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository, UserRepository userRepository, PortfolioHistoryRepository portfolioHistoryRepository, RequestService requestService) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
        this.portfolioHistoryRepository = portfolioHistoryRepository;
        this.requestService = requestService;
    }


    public Portfolio createAndSavePortfolio(PortfolioDTO portfolioDTO, User user) {
//        public void createAndSavePortfolio(PortfolioDTO portfolioDTO, HttpSession session) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setDescription(portfolioDTO.getDescription());
        portfolio.setUser(user);
//        portfolio.setUserId(1L);
//        portfolio.setEmail("testuser@gmail.com");
        portfolioRepository.save(portfolio);
        return portfolio;
    }



    public Portfolio getPortfolioById(long id) {
        return portfolioRepository.findById(id);
    }

    public Map<Long, String> getPortfoliosNamesByUsername(String email) {

        List<Portfolio> portfolios = (List<Portfolio>) portfolioRepository.findAll();
        Map<Long, String> portfolioNames = new HashMap<>();

        for (Portfolio portfolio : portfolios) {
            if (portfolio.getUser().getEmail().equals(email)) {
                portfolioNames.put(portfolio.getId(), portfolio.getName());
            }
        }
        return portfolioNames;
    }
}
