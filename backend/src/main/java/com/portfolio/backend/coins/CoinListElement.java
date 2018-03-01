package com.portfolio.backend.coins;

public class CoinListElement {

    private String logoUrl;
    private String nameShort;
    private String nameLong;
    private String price;
    private String marketCap;

    public CoinListElement(String logoUrl, String nameShort, String nameLong, String price, String marketCap) {
        this.logoUrl = logoUrl;
        this.nameShort = nameShort;
        this.nameLong = nameLong;
        this.price = price;
        this.marketCap = marketCap;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public String getPrice() {
        return price;
    }

    public String getMarketCap() {
        return marketCap;
    }
}
