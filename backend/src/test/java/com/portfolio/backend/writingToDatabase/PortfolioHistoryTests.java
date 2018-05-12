package com.portfolio.backend.writingToDatabase;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.PortfolioHistoryDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.service.PortfolioHistoryService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PortfolioHistoryTests {

    @Autowired
    public PortfolioHistoryService portfolioHistoryService;

    @Autowired
    public PortfolioService portfolioService;

    @Autowired
    public UserService userService;

    @Test
    public void testSavingHistory() throws IOException, JSONException {
        UserDTO userToSave = new UserDTO("testuser@gmail.com", "password", "test");
        userService.createAndSaveUser(userToSave);
        User user = userService.getUserBy(1L);
        PortfolioDTO port = new PortfolioDTO("test", "portfolio for testing");
        portfolioService.createAndSavePortfolio(port, user);
        Portfolio portfolio = portfolioService.getPortfolioBy("test");
        PortfolioHistoryDTO dto = new PortfolioHistoryDTO(1L, 200);
        portfolioHistoryService.createAndSaveValue(dto, portfolio);
        PortfolioHistory portfolioHistory = portfolioHistoryService.getHistoryByPortfolio(portfolio);
        Assert.assertTrue(portfolioHistory != null);
        portfolioHistoryService.deleteAll();
    }
}
