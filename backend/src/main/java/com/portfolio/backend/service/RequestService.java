package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.coins.CoinSummaries;
import com.portfolio.backend.coins.CoinSummary;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class RequestService {

    private CoinSummaries coinSummaries = new CoinSummaries();

    public void getMarketSummary() throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://bittrex.com/api/v1.1/public/getmarketsummaries",
                String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        coinSummaries.setCoinSummaries(getSummaryInstancesFromJSON(jsonObject.getString("result")));
        System.out.println("BTC price:" + coinSummaries.getPriceForBTC());
        System.out.println("ADA price:" + coinSummaries.getPriceFor("ADA"));
        System.out.println("LTC price:" + coinSummaries.getPriceFor("LTC"));
        System.out.println("XRP price:" + coinSummaries.getPriceFor("XRP"));

    }

    private List<CoinSummary> getSummaryInstancesFromJSON(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<CoinSummary>>(){});
    }
}
