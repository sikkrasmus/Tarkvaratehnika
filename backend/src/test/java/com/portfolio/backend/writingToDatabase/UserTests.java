package com.portfolio.backend.writingToDatabase;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.entities.User;
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
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSavingUser() {
        String email = "testuser@gmail.com";
        UserDTO userToSave = new UserDTO(email, "password", "test");
        userService.createAndSaveUser(userToSave);
        User userToRead = userService.getUserBy(email);
        Assert.assertEquals(userToRead.getEmail(), email);
        userService.deleteUserWith("test");
    }
}
