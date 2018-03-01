package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.coins.APIFormat;
import com.portfolio.backend.repository.CoinRepository;
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

    public void getMarketSummary() throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
//                "https://bittrex.com/api/v1.1/public/getmarketsummaries",
                "https://bittrex.com/api/v2.0/pub/markets/GetMarketSummaries",
                String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
//        System.out.println(jsonObject.getString("result"));
        List<APIFormat> result = getFormatFromRequest(jsonObject.getString("result"));
        coinRepository.setApiFormatList(result);
    }


    private List<APIFormat> getFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<APIFormat>>() {
        });
    }

//    public List<>

    public CoinRepository getCoinRepository() {
        return coinRepository;
    }
}
