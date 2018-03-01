package com.portfolio.backend.coins;

public class CoinMarketInfo {

    private String MarketCurrency;
    private String BaseCurrency;
    private String MarketCurrencyLong;
    private String BaseCurrencyLong;
    private String MinTradeSize;
    private String MarketName;
    private String IsActive;
    private String Created;
    private String Notice;
    private String IsSponsored;
    private String LogoUrl;

    public String getMarketCurrency() {
        return MarketCurrency;
    }

    public String getBaseCurrency() {
        return BaseCurrency;
    }

    public String getMarketCurrencyLong() {
        return MarketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return BaseCurrencyLong;
    }

    public String getMinTradeSize() {
        return MinTradeSize;
    }

    public String getMarketName() {
        return MarketName;
    }

    public String getIsActive() {
        return IsActive;
    }

    public String getCreated() {
        return Created;
    }

    public String getNotice() {
        return Notice;
    }

    public String getIsSponsored() {
        return IsSponsored;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }
}
