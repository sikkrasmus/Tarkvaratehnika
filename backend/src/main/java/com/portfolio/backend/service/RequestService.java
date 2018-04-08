package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.coins.bittrex.APIFormatBittrex;
import com.portfolio.backend.coins.coinmarketcap.APIFormatCMC;
import com.portfolio.backend.coins.CoinListElement;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.repository.APIRequestRepository;
import com.portfolio.backend.repository.CoinNamesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private APIRequestRepository APIRequestRepository = new APIRequestRepository();
    private final CoinNamesRepository coinNamesRepository;

    @Autowired
    public RequestService(CoinNamesRepository coinNamesRepository) {
        this.coinNamesRepository = coinNamesRepository;
    }

    public void makeAllRequests() throws IOException, JSONException {
        getMarketSummaryFromBittrex();
    }

    public void createAndUpdateCoinNames() throws IOException, JSONException {
        getResultFromBittrex();
        List<CoinNamesDTO> nameList = APIRequestRepository.getNamesFromResult();
        List<CoinNames> names = new ArrayList<>();
        for (CoinNamesDTO dto : nameList) {
            CoinNames item = new CoinNames();
            item.setShortname(dto.getShortName());
            item.setLongname(dto.getLongname());
            names.add(item);
        }
        coinNamesRepository.save(names);
    }

    public void getMarketSummaryFromBittrex() throws IOException, JSONException {
        APIRequestRepository.setPriceForBTC();
        getMarketSummaryFromCoinMarketCap();
        APIRequestRepository.createCoinList();
    }

    public void getResultFromBittrex() throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://bittrex.com/api/v2.0/pub/markets/GetMarketSummaries",
                String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        APIRequestRepository.setApiFormatBittrexList(getBittrexFormatFromRequest(jsonObject.getString("result")));
    }

    public void getMarketSummaryFromCoinMarketCap() throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.coinmarketcap.com/v1/ticker/?limit=0",
                String.class);
        JSONArray jsonObject = new JSONArray(response.getBody());
        List<APIFormatCMC> result = getCMCFormatFromRequest(jsonObject.toString());
        APIRequestRepository.setApiFormatCMCList(result);
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
        return APIRequestRepository.getCoinList();
    }

    public APIRequestRepository getAPIRequestRepository() {
        return APIRequestRepository;
    }

    public static double getPriceForPortfolio(Long portfoli0ID) {
        return 999;
    }
}
