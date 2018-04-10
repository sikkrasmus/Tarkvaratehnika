package com.portfolio.backend.writingToDatabase;


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
public class CoinNamesTests {

    @Autowired
    RequestService requestService;

    @Autowired
    CoinNamesService coinNamesService;

    @Test
    public void testSavingCoinNamesList() {
        try {
            requestService.createAndUpdateCoinNames();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        CoinNames names = coinNamesService.getCoinNamesBy("BTC");
        Assert.assertEquals(names.getLongname(), "Bitcoin");
        coinNamesService.deleteAll();
    }
}
