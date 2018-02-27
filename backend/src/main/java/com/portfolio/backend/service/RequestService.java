package com.portfolio.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    List<CoinSummary> coinSummaries;

    public void getMarketSummary() throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://bittrex.com/api/v1.1/public/getmarketsummaries",
                String.class);
//        JSONObject jsonObject = new JSONObject(response.toString());
//        coinSummaries = getSummaryInstancesFromJSON(jsonObject.getString("result"));
//
//        System.out.println(coinSummaries);
    }

    private List<CoinSummary> getSummaryInstancesFromJSON(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<List<CoinSummary>>(){});
    }
}
