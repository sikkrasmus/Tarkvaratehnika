package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }



    public void createAndSavePortfolio(PortfolioDTO portfolioDTO, User user) {
//        public void createAndSavePortfolio(PortfolioDTO portfolioDTO, HttpSession session) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setDescription(portfolioDTO.getDescription());
        portfolio.setUser(user);
//        portfolio.setUserId(1L);
//        portfolio.setEmail("testuser@gmail.com");
        portfolioRepository.save(portfolio);
    }

    //    public List<String> getAllUserPortfolios(Long id) {
//        List<String> portfolios = new ArrayList<>();
//        Iterable<Portfolio> allPortfolios = portfolioRepository.findAll();
//
//        for (Portfolio p : allPortfolios) {
////           if (p.getUserId() == id) {
//               portfolios.add(p.getName());
//           }
//        }
//        return portfolios;
//}
    public Portfolio getPortfolioById(long id) {
        return portfolioRepository.findById(id);
    }
}
