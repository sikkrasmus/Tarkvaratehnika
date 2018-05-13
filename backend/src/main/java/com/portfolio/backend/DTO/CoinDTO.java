package com.portfolio.backend.DTO;

import java.sql.Timestamp;

public class CoinDTO {

    private String shortName;
    private String longName;
    private String exchange;
    private long amount;
    private Timestamp timeAdded;
    private double priceBought;
    private long portfolioId;
    private long priceSold;

    public CoinDTO() {
    }

    public CoinDTO(String shortName, String longName, String exchange, long amount,
                   Timestamp timeAdded, double priceBought, long portfolioId, long priceSold) {
        this.shortName = shortName;
        this.longName = longName;
        this.exchange = exchange;
        this.amount = amount;
        this.timeAdded = timeAdded;
        this.priceBought = priceBought;
        this.priceSold = priceSold;
        this.portfolioId = portfolioId;
    }

    public double getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(double priceBought) {
        this.priceBought = priceBought;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public long getPriceSold() {
        return priceSold;
    }
}
