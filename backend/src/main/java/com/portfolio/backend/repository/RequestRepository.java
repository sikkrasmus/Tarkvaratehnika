package com.portfolio.backend.repository;

import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.DTO.bittrex.BittrexTotalMarketFormat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestRepository {

    private List<BittrexTotalMarketFormat> bittrexTotalMarketFormatList;
    private float BTCPrice = -1;


    public List<CoinNamesDTO> getNamesFromBittrexResult() {
        List<CoinNamesDTO> nameList = new ArrayList<>();
        for (BittrexTotalMarketFormat coin : bittrexTotalMarketFormatList) {
            nameList.add(new CoinNamesDTO(coin.getMarket().getMarketCurrency(), coin.getMarket().getMarketCurrencyLong()));
        }
        return nameList;
    }


    public void setPriceForBTC() {
        for (BittrexTotalMarketFormat f : bittrexTotalMarketFormatList) {
            if (f.getMarket().getMarketName().equals("USDT-BTC")) {
                BTCPrice = Float.parseFloat(f.getSummary().getLast());
            }
        }
    }

    public void setBittrexTotalMarketFormatList(List<BittrexTotalMarketFormat> bittrexTotalMarketFormatList) {
        this.bittrexTotalMarketFormatList = bittrexTotalMarketFormatList;
    }


    public List<BittrexTotalMarketFormat> getBittrexTotalMarketFormatList() {
        return bittrexTotalMarketFormatList;
    }


    public Map<String, String> getIconUrlMap() {
        Map<String, String> urls = new HashMap<>();
        for (BittrexTotalMarketFormat item : bittrexTotalMarketFormatList) {
            urls.put(item.getMarket().getMarketCurrency(), item.getMarket().getLogoUrl());
        }
        return urls;
    }

    public float getBTCPrice() {
        return BTCPrice;
    }
}
