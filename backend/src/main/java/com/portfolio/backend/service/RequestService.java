package com.portfolio.backend.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.DTO.bittrex.BittrexTotalMarketFormat;
import com.portfolio.backend.DTO.bittrex.BittrexSingleMarketFormat;
import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.RequestRepository;
import com.portfolio.backend.repository.CoinNamesRepository;
import com.portfolio.backend.repository.CoinRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    private RequestRepository RequestRepository = new RequestRepository();
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
        List<CoinNamesDTO> nameList = RequestRepository.getNamesFromBittrexResult();
        List<CoinNames> names = new ArrayList<>();
        for (CoinNamesDTO dto : nameList) {
            CoinNames item = new CoinNames();
            item.setShortname(dto.getShortName());
            item.setLongname(dto.getLongname());
            names.add(item);
        }
        coinNamesRepository.save(names);
    }

    public BittrexSingleMarketFormat getMarketSummaryFromBittrexForOneCoin(String shortName) throws JSONException, IOException {
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
        RequestRepository.setPriceForBTC();
//        RequestRepository.createCoinList();
    }

    public void getResultFromBittrex() throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://bittrex.com/api/v2.0/pub/markets/GetMarketSummaries",
                String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        RequestRepository.setBittrexTotalMarketFormatList(getBittrexFormatFromRequest(jsonObject.getString("result")));
    }


    private List<BittrexTotalMarketFormat> getBittrexFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<BittrexTotalMarketFormat>>() {
        });
    }

    private List<BittrexSingleMarketFormat> getSingleMarketFormatFromRequest(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(json, new TypeReference<List<BittrexSingleMarketFormat>>() {
        });
    }

    public RequestRepository getRequestRepository() {
        return RequestRepository;
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
        BittrexSingleMarketFormat market = getMarketSummaryFromBittrexForOneCoin(coin.getShortname());
        return market.getLast();
    }

    public void saveAllImagesFromBittrex() throws IOException, JSONException {
        getResultFromBittrex();
        Map<String, String> urlMap = RequestRepository.getIconUrlMap();
        Iterator it = urlMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            BufferedImage image;
            try {
                System.out.println("saving image for: " + pair.getKey());
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
        BittrexSingleMarketFormat market = getMarketSummaryFromBittrexForOneCoin(coin.getShortname());
        return String.format("%.2f", ((market.getLast() - market.getPrevDay()) / market.getLast()) * 100) + "%";
    }

    public double getProfitForPortfolio(Long portfolioId) throws IOException, JSONException, ParseException {
        List<Coin> coins = coinRepository.findAllByPortfolioId(portfolioId);
        double profit = 0;
        for (Coin c : coins) {
            double bought = c.getAmount() * c.getPricebought();
            double current = c.getAmount() * getPriceFor(c);
            profit += current - bought;
        }
        return profit;
    }

    public double getTotalPriceForPortfolio(Long portfolioId) throws IOException, JSONException, ParseException {
        List<Coin> coins = coinRepository.findAllByPortfolioId(portfolioId);
        double total = 0;
        for (Coin c : coins) {
            total += c.getAmount() * getPriceFor(c);
        }
        return total;
    }
}
