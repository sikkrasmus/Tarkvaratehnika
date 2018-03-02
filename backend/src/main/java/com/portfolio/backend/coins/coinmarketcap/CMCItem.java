package com.portfolio.backend.coins.coinmarketcap;

public class CMCItem {

    private String marketCap;
    private String change24h;

    public CMCItem(String marketCap, String change24h) {
//        System.out.println(marketCap);
        if (marketCap == null) {
            this.marketCap = "0";
        } else {
            this.marketCap = marketCap;
        }
//        this.marketCap = "300";
        this.change24h = change24h;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getChange24h() {
        return change24h;
    }
}
