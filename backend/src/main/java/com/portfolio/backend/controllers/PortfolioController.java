package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.DTO.UserPortfolioDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.repository.CoinRepository;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
public class PortfolioController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @PostMapping("/addPortfolio")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> savePortfolio(@RequestBody UserPortfolioDTO userPortfolioDTO){
        PortfolioDTO portfolioDTO = new PortfolioDTO(userPortfolioDTO.getPortfolioName(), userPortfolioDTO.getDescription());

        User user = userService.findUser(userPortfolioDTO.getEmail());
        Portfolio portfolio = portfolioService.createAndSavePortfolio(portfolioDTO, user);

        Map<String, String> data = new HashMap<>();
        data.put("description", portfolio.getName());
        data.put("portfolioName", portfolio.getDescription());
        data.put("username", user.getEmail());

        return data;
    }

    @PostMapping("/getPortfolio")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<Long, String> getPortfolio(@RequestBody UserDTO userDTO){
        return portfolioService.getPortfoliosNamesByUsername(userDTO.getEmail());
    }

    @PostMapping("/addCoin")
    @ResponseStatus(HttpStatus.CREATED)
    public CoinDTO addCoin(@RequestBody CoinDTO coinDTO){
        Portfolio portfolio = portfolioService.getPortfolioById(coinDTO.getPortfolioId());
        coinService.createAndSaveCoin(coinDTO, portfolio);

        System.out.println(coinDTO.getLongName());
        System.out.println(coinDTO.getPriceBought());

        return coinDTO;
    }

    @PostMapping("/getGraph")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<Timestamp, Double> getHistory(@RequestBody PortfolioDTO portfolioDTO) {
        Portfolio portfolio = portfolioService.getPortfolioBy(portfolioDTO.getPortfolioId());
        Set<PortfolioHistory> histories = portfolio.getHistories();
        Map<Timestamp, Double> result = new HashMap<>();
        for (PortfolioHistory history : histories) {
            result.put(history.getTime(), history.getValue());
        }
        return result;
    }


}
