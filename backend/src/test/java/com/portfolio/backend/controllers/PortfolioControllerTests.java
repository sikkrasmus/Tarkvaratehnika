package com.portfolio.backend.controllers;


import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private CoinService coinService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Test
    public void testCreatingPortfolio() {

    }
}
