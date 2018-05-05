package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.coins.bittrex.APIFormatBittrex;
import com.portfolio.backend.coins.bittrex.SingleMarketFormat;
import com.portfolio.backend.coins.coinmarketcap.APIFormatCMC;
import com.portfolio.backend.coins.CoinListElement;
import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.repository.APIRequestRepository;
import com.portfolio.backend.repository.CoinNamesRepository;
import com.portfolio.backend.repository.CoinRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    private APIRequestRepository APIRequestRepository = new APIRequestRepository();
    private final CoinNamesRepository coinNamesRepository;
    private final CoinRepository coinRepository;

    @Autowired
    public RequestService(CoinNamesRepository coinNamesRepository, CoinRepository coinRepository) {
        this.coinNamesRepository = coinNamesRepository;
        this.coinRepository = coinRepository;
    }

    public void makeAllRequests() throws IOException, JSONException {
        getMarketSummaryFromBittrex();
    }

    public void createAndUpdateCoinNames() throws IOException, JSONException {
        getResultFromBittrex();
        List<CoinNamesDTO> nameList = APIRequestRepository.getNamesFromResult();
        List<CoinNames> namesInDb = (List<CoinNames>) coinNamesRepository.findAll();
        List<CoinNames> names = new ArrayList<>();
        for (CoinNamesDTO dto : nameList) {
            CoinNames item = new CoinNames();
            item.setShortname(dto.getShortName());
            item.setLongname(dto.getLongname());
            if (!namesInDb.contains(item)){
                names.add(item);
            }
        }
        coinNamesRepository.save(names);
    }

    public SingleMarketFormat getMarketSummaryFromBittrexForOneCoin(String shortName) throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url;
        if (shortName.equals("BTC")) {
            url = "https://bittrex.com/api/v1.1/public/getmarketsummary?market=usdt-" + shortName;
        } else {
            url = "https://bittrex.com/api/v1.1/public/getmarketsummary?market=btc-" + shortName;
        }
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        return getSingleMarketFormatFromRequest(jsonObject.getString("result")).get(0);
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

    private List<SingleMarketFormat> getSingleMarketFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<SingleMarketFormat>>() {
        });
    }



    public List<CoinListElement> getCoinList() {
        return APIRequestRepository.getCoinList();
    }

    public APIRequestRepository getAPIRequestRepository() {
        return APIRequestRepository;
    }

    public double getPriceForPortfolio(Long portfolioId) throws IOException, JSONException {
        double price = 0;
        List<Coin> allCoins = (List<Coin>) coinRepository.findAll();
        List<Coin> portfolioCoins = new ArrayList<>();
        for (Coin c : allCoins) {
            if (c.getPortfolio().getId() == portfolioId) {
                portfolioCoins.add(c);
            }
        }
        for (Coin coin : portfolioCoins) {
            price += getPriceFor(coin);
        }
        return price;
    }

    public double getPriceFor(Coin coin) throws IOException, JSONException {
        SingleMarketFormat market = getMarketSummaryFromBittrexForOneCoin(coin.getShortname());
        return market.getLast();
    }

    public void saveAllImagesFromBittrex() throws IOException, JSONException {
        getResultFromBittrex();
        Map<String, String> urlMap = APIRequestRepository.getIconUrlMap();
        Iterator it = urlMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            BufferedImage image;
            try {
                if (pair.getValue() != null) {
                    URL url = new URL(pair.getValue().toString());
                    image = ImageIO.read(url);
                    ImageIO.write(image, "png", new File("frontend/src/assets/coins/" + pair.getKey() + ".png"));
                    it.remove();
                }
            } catch (IOException e) {
            }
        }
    }

    public String getValueChangeForCoin(Coin coin) throws IOException, JSONException {
        SingleMarketFormat market = getMarketSummaryFromBittrexForOneCoin(coin.getShortname());
        return String.format("%.2f", ((market.getLast() - market.getPrevDay()) / market.getLast()) * 100) + "%";
    }
}
