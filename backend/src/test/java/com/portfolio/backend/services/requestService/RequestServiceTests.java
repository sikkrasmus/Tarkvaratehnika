package com.portfolio.backend.services.requestService;

import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.helper.RequestHelper;
import com.portfolio.backend.repository.CoinRepository;
import com.portfolio.backend.repository.RequestRepository;
import com.portfolio.backend.service.CoinNamesService;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.RequestService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RequestServiceTests {

    @Autowired
    RequestService requestService;

    @Autowired
    CoinNamesService coinNamesService;

    @Autowired
    private CoinRepository coinRepository;

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


    @Ignore
    @Test
    public void testGettingTotalPriceForPortfolio() throws IOException, JSONException, ParseException {
        Coin c1 = new Coin();
        Coin c2 = new Coin();
        Coin c3 = new Coin();
        c1.setAmount(2.5);
        c1.setShortname("BTC");
        c2.setAmount(1);
        c2.setShortname("LTC");
        c3.setAmount(-5);
        c3.setShortname("BCC");
        List<Coin> coins = Arrays.asList(c1, c2, c3);
        Mockito.when(requestService.getPriceFor(any(Coin.class))).thenReturn(55.4);
//        Mockito.when(requestService.getPriceFor(c2)).thenReturn(500.0);
//        Mockito.when(requestService.getPriceFor(c3)).thenReturn(500.0);

        // -1194.6
        Mockito.when(coinRepository.findAllByPortfolioId(Mockito.anyLong())).thenReturn(coins);
        Assert.assertEquals( -83.1, requestService.getTotalPriceForPortfolio(1L), 0.01f);
    }

}
