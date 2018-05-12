package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.DTO.UserPortfolioDTO;
import com.portfolio.backend.entities.*;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@CrossOrigin
@RestController
public class PortfolioController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping("/addPortfolio")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> savePortfolio(@RequestBody UserPortfolioDTO userPortfolioDTO) {
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
    public Map<Long, String> getPortfolio(@RequestBody UserDTO userDTO) {
        return portfolioService.getPortfoliosNamesByUsername(userDTO.getEmail());
    }

    @PostMapping("/addCoin")
    @ResponseStatus(HttpStatus.CREATED)
    public CoinDTO addCoin(@RequestBody CoinDTO coinDTO) {
        Portfolio portfolio = portfolioService.getPortfolioById(coinDTO.getPortfolioId());
        coinService.createAndSaveCoin(coinDTO, portfolio);

        return coinDTO;
    }

    @PostMapping("/getPortfolioCoins")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String[]> getPortfolioCoins(@RequestBody PortfolioDTO portfolioDTO) throws IOException, JSONException {

        Map<String, String[]> coins = new HashMap<>();
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioDTO.getPortfolioId());


        for (Coin coin : portfolio.getCoins()) {
            if (coin.getLongname() != null) {

                coins.put(coin.getLongname(), new String[]{coin.getShortname(),
                        String.valueOf(coin.getAmount()), String.valueOf(requestService.getPriceFor(coin)),
                        requestService.getValueChangeForCoin(coin)});
            }
        }
        return coins;

    }
    @PostMapping("/getGraphData")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<Timestamp, Double> getGraph(@RequestBody PortfolioDTO portfolioDTO) {

        Portfolio portfolio = portfolioService.getPortfolioBy(portfolioDTO.getPortfolioId());
        Set<PortfolioHistory> histories = portfolio.getHistories();

        Map<Timestamp, Double> result = new TreeMap<>();
        for (PortfolioHistory history : histories) {
            result.put(history.getTime(), history.getValue());
        }
        return result;
    }

    @PostMapping("/getAllCoins")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CoinNames> getAllCoins(){
        return coinService.getAllCoins();
    }


}
