package com.portfolio.backend.services.requestService;

import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.helper.RequestHelper;
import com.portfolio.backend.repository.RequestRepository;
import com.portfolio.backend.service.CoinNamesService;
import com.portfolio.backend.service.RequestService;
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
public class RequestServiceTests {

    @Autowired
    RequestService requestService;

    @Autowired
    CoinNamesService coinNamesService;

    RequestHelper requestHelper = new RequestHelper();
    RequestRepository requestRepository = new RequestRepository();

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

    @Test
    public void testGettingMarketSummaryForOneCoinFromBittrex() throws IOException, JSONException {
        Assert.assertEquals("BTC-ETH", requestService.getMarketSummaryFromBittrexForOneCoin("ETH").getMarketName());
    }

    @Test
    public void testGettingPercentChange() {

    }

}
