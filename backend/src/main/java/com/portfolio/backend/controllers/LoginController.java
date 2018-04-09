package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CoinService coinService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> login(@RequestBody UserDTO userDTO, HttpSession session) throws IOException, JSONException {
        if (userService.validateUser(userDTO)) {
            Map<String, String> data = new HashMap<>();
            session.setAttribute("name", userDTO.getEmail());
            data.put("username", userDTO.getEmail());
            data.put("sessionid", session.getId());
            testStuff();
            return data;
        } else {
            return null;
        }
    }

    private void testStuff() throws IOException, JSONException {
//        requestService.createAndUpdateCoinNames();
//        User user = userService.getUserBy(1);
//        PortfolioDTO portfolioDTO = new PortfolioDTO("altcoins", "all the altcoins");
//        portfolioService.createAndSavePortfolio(portfolioDTO, user);
//        Portfolio p = portfolioService.getPortfolioById(1);
//        CoinDTO coin = new CoinDTO("ETH", "Ethereum", "Bittrex", 2L,
//                new Timestamp(System.currentTimeMillis()), 342.2);
//        coinService.createAndSaveCoin(coin, p);
//        portfolioService.saveCurrentPriceForAllPortfolios();

    }
}
