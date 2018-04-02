package com.portfolio.backend.coins;

public class CoinListElement {

    private String logoUrl;
    private String nameShort;
    private String nameLong;
    private String price;
    private double marketCap;
    private String change24h;
    private String percentColor;

    public CoinListElement(String logoUrl, String nameShort, String nameLong, String price, String marketCap,
                           String change24h) {
        this.logoUrl = logoUrl;
        this.nameShort = nameShort;
        this.nameLong = nameLong;
        this.price = price;
        if (marketCap != null) {
            this.marketCap = Double.parseDouble(marketCap);
        } else {
            this.marketCap = 0;
        }
        this.change24h = change24h;
        if (change24h.charAt(0) == '-') {
            percentColor = "red_text";
        } else {
            percentColor = "green_text";
        }
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

    public double getMarketCap() {
        return marketCap;
    }

    public String getChange24h() {
        return change24h;
    }

    public String getPercentColor() {
        return percentColor;
    }
}
