package com.portfolio.backend.writingToDatabase;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

//import com.portfolio.backend.config.TestsConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PortfolioTests {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @Test
    public void testSavingPortfolio() {
        PortfolioDTO dto = new PortfolioDTO("test", "portfolio for testing");
        portfolioService.createAndSavePortfolio(dto, null);
        Portfolio portfolio = portfolioService.getPortfolioBy("test");
        Assert.assertEquals(portfolio.getName(), "test");
        portfolioService.deletePortfolioByName("test");
    }

    @Test
    public void testAssigningUserToPortfolioWhenSaving() {
        UserDTO userToSave = new UserDTO("testuser@gmail.com", "password", "test");
        userService.createAndSaveUser(userToSave);
        User user = userService.getUserBy(1L);
        PortfolioDTO toSave = new PortfolioDTO("test1", "portfolio for testing1");
        portfolioService.createAndSavePortfolio(toSave, user);
        Assert.assertEquals(portfolioService.getPortfolioBy("test1").getUser(), user);
        portfolioService.deletePortfolioByName("test1");
        userService.deleteUserWith("test");
    }
}
