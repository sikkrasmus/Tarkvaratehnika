package com.portfolio.backend.DTO.bittrex;

public class BittrexTotalMarketFormat {

    private CoinMarket Market;
    private CoinSummary Summary;
    private String IsVerified;

    public BittrexTotalMarketFormat(CoinMarket market, CoinSummary summary, String isVerified) {
        Market = market;
        Summary = summary;
        IsVerified = isVerified;
    }

    public BittrexTotalMarketFormat() {
    }

    public CoinMarket getMarket() {
        return Market;
    }

    public CoinSummary getSummary() {
        return Summary;
    }

    public String getIsVerified() {
        return IsVerified;
    }
}
