package com.portfolio.backend.coins.bittrex;

public class SingleMarketFormat {

    private String MarketName;
    private double High;
    private double Low;
    private double Volume;
    private double Last;
    private double BaseVolume;
    private String TimeStamp;
    private double Bid;
    private double Ask;
    private int OpenBuyOrders;
    private int OpenSellOrders;
    private double PrevDay;
    private String Created;

    public String getMarketName() {
        return MarketName;
    }

    public double getHigh() {
        return High;
    }

    public double getLow() {
        return Low;
    }

    public double getVolume() {
        return Volume;
    }

    public double getLast() {
        return Last;
    }

    public double getBaseVolume() {
        return BaseVolume;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public double getBid() {
        return Bid;
    }

    public double getAsk() {
        return Ask;
    }

    public int getOpenBuyOrders() {
        return OpenBuyOrders;
    }

    public int getOpenSellOrders() {
        return OpenSellOrders;
    }

    public double getPrevDay() {
        return PrevDay;
    }

    public String getCreated() {
        return Created;
    }
}
