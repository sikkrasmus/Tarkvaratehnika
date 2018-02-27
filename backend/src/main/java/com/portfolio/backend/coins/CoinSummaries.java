package com.portfolio.backend.coins;

import java.util.List;

public class CoinSummaries {

    private List<CoinSummary> coinSummaries;


    public void setCoinSummaries(List<CoinSummary> coinSummaries) {
        this.coinSummaries = coinSummaries;
    }

    public float getPriceForBTC() {
        for (CoinSummary summary : coinSummaries) {
            if (summary.getMarketName().equals("USDT-BTC")) {
                return Float.parseFloat(summary.getLast());
            }
        }
        return -1;
    }

    public float getPriceFor(String coin) {
        for (CoinSummary summary : coinSummaries) {
            if (("BTC-" + coin).equals(summary.getMarketName())) {
                return Float.parseFloat(summary.getLast()) * getPriceForBTC();
            }
        }
        return -1;
    }
}
