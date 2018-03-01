package com.portfolio.backend.repository;

import com.portfolio.backend.coins.APIFormatBittrex;
import com.portfolio.backend.coins.APIFormatCMC;
import com.portfolio.backend.coins.CoinListElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinRepository {

    private List<APIFormatBittrex> apiFormatBittrexList;
    private List<APIFormatCMC> apiFormatCMCList;
    private Map<String, String> nameMarketCapMap = new HashMap<>();

    private List<CoinListElement> coinList = new ArrayList<>();
    private float BTCPrice = -1;




    public void setPriceForBTC() {
        for (APIFormatBittrex f : apiFormatBittrexList) {
            if (f.getMarket().getMarketName().equals("USDT-BTC")) {
                BTCPrice = Float.parseFloat(f.getSummary().getLast());
            }
        }
    }

    public void createCoinList() {
        mapNameToMarketCap();
        float price;

        for (APIFormatBittrex a : apiFormatBittrexList) {
            boolean isBTC = a.getMarket().getMarketName().equals("USDT-BTC");
            if (a.getMarket().getBaseCurrency().equals("BTC") || isBTC) {
                if (!isBTC) {
                    price = Float.parseFloat(a.getSummary().getLast()) * BTCPrice;
                } else {
                    price = Float.parseFloat(a.getSummary().getLast());
                }

                coinList.add(new CoinListElement(
                        a.getMarket().getLogoUrl(),
                        a.getMarket().getMarketCurrency(),
                        a.getMarket().getMarketCurrencyLong(),
                        String.valueOf(price),
                        nameMarketCapMap.get(a.getMarket().getMarketCurrency())));
            }
        }
    }

    private void mapNameToMarketCap() {
        for (APIFormatCMC coin : apiFormatCMCList) {
            nameMarketCapMap.put(coin.getSymbol(), coin.getMarket_cap_usd());
        }
    }


    public void setApiFormatBittrexList(List<APIFormatBittrex> apiFormatBittrexList) {
        this.apiFormatBittrexList = apiFormatBittrexList;
    }

    public void setApiFormatCMCList(List<APIFormatCMC> apiFormatCMCList) {
        this.apiFormatCMCList = apiFormatCMCList;
    }

    public List<APIFormatBittrex> getApiFormatBittrexList() {
        return apiFormatBittrexList;
    }

    public List<CoinListElement> getCoinList() {
        return coinList;
    }
}
