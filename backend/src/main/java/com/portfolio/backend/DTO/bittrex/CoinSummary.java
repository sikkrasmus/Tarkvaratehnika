package com.portfolio.backend.DTO.bittrex;

public class CoinSummary {

    private String MarketName;
    private String High;
    private String Low;
    private String Volume;
    private String Last;
    private String BaseVolume;
    private String TimeStamp;
    private String Bid;
    private String Ask;
    private String OpenBuyOrders;
    private String OpenSellOrders;
    private String PrevDay;
    private String Created;

    public CoinSummary() {
    }

    public CoinSummary(String marketName, String high, String low, String volume, String last, String baseVolume, String timeStamp, String bid, String ask, String openBuyOrders, String openSellOrders, String prevDay, String created) {
        MarketName = marketName;
        High = high;
        Low = low;
        Volume = volume;
        Last = last;
        BaseVolume = baseVolume;
        TimeStamp = timeStamp;
        Bid = bid;
        Ask = ask;
        OpenBuyOrders = openBuyOrders;
        OpenSellOrders = openSellOrders;
        PrevDay = prevDay;
        Created = created;
    }

    public String getMarketName() {
        return MarketName;
    }

    public String getHigh() {
        return High;
    }

    public String getLow() {
        return Low;
    }

    public String getVolume() {
        return Volume;
    }

    public String getLast() {
        return Last;
    }

    public String getBaseVolume() {
        return BaseVolume;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public String getBid() {
        return Bid;
    }

    public String getAsk() {
        return Ask;
    }

    public String getOpenBuyOrders() {
        return OpenBuyOrders;
    }

    public String getOpenSellOrders() {
        return OpenSellOrders;
    }

    public String getPrevDay() {
        return PrevDay;
    }

    public String getCreated() {
        return Created;
    }

}
