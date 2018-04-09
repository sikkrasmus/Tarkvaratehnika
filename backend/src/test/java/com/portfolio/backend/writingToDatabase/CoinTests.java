package com.portfolio.backend.writingToDatabase;

import com.portfolio.backend.service.CoinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CoinTests {

    @Autowired
    public CoinService coinService;

    @Test
    public void TestSavingCoin() {

    }
}
