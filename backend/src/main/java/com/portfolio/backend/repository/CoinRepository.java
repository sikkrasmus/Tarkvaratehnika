package com.portfolio.backend.repository;

import com.portfolio.backend.coins.APIFormat;

import java.util.List;

public class CoinRepository {

    private List<APIFormat> apiFormatList;


    public float getPriceForBTC() {
        for (APIFormat f : apiFormatList) {
            if (f.getMarket().getMarketName().equals("BTC")) {
                return Float.parseFloat(f.getSummary().getLast());
            }
        }
        return -1;
    }

    public float getPriceFor(String coin) {
        for (APIFormat f : apiFormatList) {
            if (f.getMarket().getMarketCurrency().equals(coin)) {
                return Float.parseFloat(f.getSummary().getLast()) * getPriceForBTC();
            }
        }
        return -1;
    }

    public String getImageFor(String coin) {
        for (APIFormat f : apiFormatList) {
            if (coin.equals(f.getMarket().getMarketCurrency())) {
                return f.getMarket().getLogoUrl();
            }
        }
        return null;
    }


    public void setApiFormatList(List<APIFormat> apiFormatList) {
        this.apiFormatList = apiFormatList;
    }

    public List<APIFormat> getApiFormatList() {
        return apiFormatList;
    }
}
