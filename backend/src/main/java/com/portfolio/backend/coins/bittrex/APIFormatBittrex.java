package com.portfolio.backend.coins.bittrex;

import com.portfolio.backend.coins.CoinMarketInfo;
import com.portfolio.backend.coins.CoinSummary;

public class APIFormatBittrex {

    private CoinMarketInfo Market;
    private CoinSummary Summary;
    private String IsVerified;

    public CoinMarketInfo getMarket() {
        return Market;
    }

    public CoinSummary getSummary() {
        return Summary;
    }

    public String getIsVerified() {
        return IsVerified;
    }
}
