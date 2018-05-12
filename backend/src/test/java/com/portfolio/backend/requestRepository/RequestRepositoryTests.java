package com.portfolio.backend.requestRepository;

import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.DTO.bittrex.BittrexTotalMarketFormat;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.helper.RequestHelper;
import com.portfolio.backend.repository.RequestRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RequestRepositoryTests {

    RequestRepository requestRepository = new RequestRepository();

    RequestHelper requestHelper = new RequestHelper();

    @Before
    public void setup() {
        requestRepository.setBittrexTotalMarketFormatList(requestHelper.getBittrexTotalList());
    }

    @Test
    public void getLongNameFromBittrexResult() {
        CoinNamesDTO c1 = new CoinNamesDTO("ETH", "Ethereum");
        CoinNamesDTO c2 = new CoinNamesDTO("BTC", "Bitcoin");
        CoinNamesDTO c3 = new CoinNamesDTO("LTC", "Litecoin");
        List<CoinNamesDTO> expected = Arrays.asList(c1, c2, c3);
        List<CoinNamesDTO> actual = requestRepository.getNamesFromBittrexResult();
        int n = 0;
        for (CoinNamesDTO c : expected) {
            Assert.assertEquals(c.getShortName(), actual.get(n).getShortName());
            Assert.assertEquals(c.getLongname(), actual.get(n).getLongname());
            n++;
        }
    }

    @Test
    public void testGettingBitcoinPrice() {
        requestRepository.setPriceForBTC();
        Assert.assertEquals(9000, requestRepository.getBTCPrice(), 0.001f);
    }
}
