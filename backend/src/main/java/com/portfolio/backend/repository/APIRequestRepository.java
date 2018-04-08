package com.portfolio.backend.repository;

import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.coins.bittrex.APIFormatBittrex;
import com.portfolio.backend.coins.coinmarketcap.APIFormatCMC;
import com.portfolio.backend.coins.CoinListElement;
import com.portfolio.backend.coins.coinmarketcap.CMCItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIRequestRepository {

    private List<APIFormatBittrex> apiFormatBittrexList;
    private List<APIFormatCMC> apiFormatCMCList;
    private Map<String, CMCItem> nameMarketCapMap = new HashMap<>();

    private List<CoinListElement> coinList = new ArrayList<>();
    private float BTCPrice = -1;


    public List<CoinNamesDTO> getNamesFromResult() {
        List<CoinNamesDTO> nameList = new ArrayList<>();
        for (APIFormatBittrex coin : apiFormatBittrexList) {
            if (coin.getMarket().getBaseCurrency().equals("BTC")) {
                nameList.add(new CoinNamesDTO(coin.getMarket().getMarketCurrency(), coin.getMarket().getMarketCurrencyLong()));
            } else if (coin.getMarket().getMarketCurrency().equals("BTC")) {
                nameList.add(new CoinNamesDTO(coin.getMarket().getMarketCurrency(), coin.getMarket().getMarketCurrencyLong()));
            }
        }
        return nameList;
    }


    public void setPriceForBTC() {
        for (APIFormatBittrex f : apiFormatBittrexList) {
            if (f.getMarket().getMarketName().equals("USDT-BTC")) {
                BTCPrice = Float.parseFloat(f.getSummary().getLast());
            }
        }
    }

    public void createCoinList() {
        coinList.clear();
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

                if (nameMarketCapMap.get(a.getMarket().getMarketCurrency()) != null) {
                    coinList.add(new CoinListElement(
                            a.getMarket().getLogoUrl(),
                            a.getMarket().getMarketCurrency(),
                            a.getMarket().getMarketCurrencyLong(),
                            String.valueOf(price),
                            nameMarketCapMap.get(a.getMarket().getMarketCurrency()).getMarketCap(),
                            nameMarketCapMap.get(a.getMarket().getMarketCurrency()).getChange24h()));
                }
            }
        }
    }

    private void mapNameToMarketCap() {
        for (APIFormatCMC coin : apiFormatCMCList) {
            nameMarketCapMap.put(coin.getSymbol(), new CMCItem(coin.getMarket_cap_usd(), coin.getPercent_change_24h()));
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
