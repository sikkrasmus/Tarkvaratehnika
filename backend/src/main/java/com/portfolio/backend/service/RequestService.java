package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.coins.bittrex.APIFormatBittrex;
import com.portfolio.backend.coins.coinmarketcap.APIFormatCMC;
import com.portfolio.backend.coins.CoinListElement;
import com.portfolio.backend.repository.CoinRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class RequestService {

    private CoinRepository coinRepository = new CoinRepository();

    public void makeAllRequests() throws IOException, JSONException {
        getMarketSummaryFromBittrex();
    }

    public void getMarketSummaryFromBittrex() throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://bittrex.com/api/v2.0/pub/markets/GetMarketSummaries",
                String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        List<APIFormatBittrex> result = getBittrexFormatFromRequest(jsonObject.getString("result"));
        coinRepository.setApiFormatBittrexList(result);
        coinRepository.setPriceForBTC();
        getMarketSummaryFromCoinMarketCap();
        coinRepository.createCoinList();
    }

    public void getMarketSummaryFromCoinMarketCap() throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.coinmarketcap.com/v1/ticker/?limit=0",
                String.class);
        JSONArray jsonObject = new JSONArray(response.getBody());
        List<APIFormatCMC> result = getCMCFormatFromRequest(jsonObject.toString());
        coinRepository.setApiFormatCMCList(result);
    }


    private List<APIFormatBittrex> getBittrexFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<APIFormatBittrex>>() {
        });
    }

    private List<APIFormatCMC> getCMCFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<APIFormatCMC>>() {
        });
    }

    public List<CoinListElement> getCoinList() {
        return coinRepository.getCoinList();
    }

    public CoinRepository getCoinRepository() {
        return coinRepository;
    }
}
