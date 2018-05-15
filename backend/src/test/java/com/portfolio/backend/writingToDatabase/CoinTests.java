package com.portfolio.backend.writingToDatabase;

import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CoinTests {

    @Autowired
    public CoinService coinService;

    @Autowired
    public PortfolioService portfolioService;

    @Autowired
    public UserService userService;

    @Autowired
    public CoinNamesService coinNamesService;

    @Test
    public void TestSavingCoin() {
        coinNamesService.deleteAll();
        coinNamesService.createAndSaveCoin(new CoinNamesDTO("ADA", "Cardano"));
        UserDTO userToSave = new UserDTO("testuser@gmail.com", "password", "test");
        userService.createAndSaveUser(userToSave);
        User user = userService.getUserBy(1L);
        PortfolioDTO toSave = new PortfolioDTO("test1", "portfolio for testing1");
        portfolioService.createAndSavePortfolio(toSave, user);
        Portfolio p = portfolioService.getPortfolioBy("test1");
        CoinDTO coinDTO = new CoinDTO("ADA", "Cardano", "Bittrex", 300.0,
                new Timestamp(System.currentTimeMillis()), 0.15, p.getId(), 280);
        coinService.createAndSaveCoin(coinDTO, p);
        Assert.assertEquals(coinService.getCoinBy(1L).getShortname(), "ADA");
        coinService.deleteAll();
        portfolioService.deletePortfolioByName("test1");
        userService.deleteAll();
        coinNamesService.deleteAll();
    }
}
