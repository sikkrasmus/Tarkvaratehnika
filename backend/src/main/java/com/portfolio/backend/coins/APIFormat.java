package com.portfolio.backend.coins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIFormat {

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
