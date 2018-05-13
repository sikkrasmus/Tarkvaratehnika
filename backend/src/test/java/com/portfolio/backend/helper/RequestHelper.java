package com.portfolio.backend.helper;

import com.portfolio.backend.DTO.bittrex.BittrexTotalMarketFormat;
import com.portfolio.backend.DTO.bittrex.CoinMarket;
import com.portfolio.backend.DTO.bittrex.CoinSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestHelper {

    private List<CoinMarket> markets;
    private List<CoinSummary> summaries;

    public RequestHelper() {
    }

    public List<BittrexTotalMarketFormat> getBittrexTotalList() {
        List<BittrexTotalMarketFormat> totalList = new ArrayList<>();
        createMarkets();
        createSummaries();

        totalList.add(new BittrexTotalMarketFormat(markets.get(0), summaries.get(0), null));
        totalList.add(new BittrexTotalMarketFormat(markets.get(1), summaries.get(1), null));
        totalList.add(new BittrexTotalMarketFormat(markets.get(2), summaries.get(2), null));
        return totalList;
    }

    private void createMarkets() {
        CoinMarket market1 = new CoinMarket("ETH", "BTC", "Ethereum",
                "Bitcoin", "0.00346548", "BTC-ETH", "true",
                "2015-08-14T09:02:24.817", null, null,
                "https://bittrexblobstorage.blob.core.windows.net/public/7e5638ef-8ca0-404d-b61e-9d41c2e20dd9.png");
        CoinMarket market2 = new CoinMarket("BTC", "USDT", "Bitcoin",
                "Tether", "0.003", "USDT-BTC", "true",
                "2015-08-14T09:02:24.817", null, null,
                "https://bittrex.com/Content/img/symbols/BTC.png");
        CoinMarket market3 = new CoinMarket("LTC", "BTC", "Litecoin",
                "Bitcoin", "0.003", "BTC-LTC", "true",
                "2015-08-14T09:02:24.817", null, null,
                "https://bittrexblobstorage.blob.core.windows.net/public/6defbc41-582d-47a6-bb2e-d0fa88663524.png");
        markets = Arrays.asList(market1, market2, market3);
    }

    private void createSummaries() {
        CoinSummary s1 = new CoinSummary("", "", "", "", "9000",
                "", "", "", "", "", "",
                "", "");
        CoinSummary s2 = new CoinSummary("", "", "", "", "9000",
                "", "", "", "", "", "",
                "", "");
        CoinSummary s3 = new CoinSummary("", "", "", "", "9000",
                "", "", "", "", "", "",
                "", "");
        summaries = Arrays.asList(s1, s1, s3);
    }
}
