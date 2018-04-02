package com.portfolio.backend.coins.coinmarketcap;

public class APIFormatCMC {

    private String id;
    private String name;
    private String symbol;
    private String rank;
    private String price_usd;
    private String price_btc;
    private String _24h_volume_usd;
    private String market_cap_usd;
    private String available_supply;
    private String total_supply;
    private String max_supply;
    private String percent_change_24h;
    private String percent_change_7d;
    private String last_updated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public String get_24h_volume_usd() {
        return _24h_volume_usd;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public String getLast_updated() {
        return last_updated;
    }
}
