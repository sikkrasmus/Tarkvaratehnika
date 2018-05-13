package com.portfolio.backend.service;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.entities.Coin;
import com.portfolio.backend.entities.PortfolioHistory;
import com.portfolio.backend.entities.User;
import com.portfolio.backend.repository.CoinRepository;
import com.portfolio.backend.repository.PortfolioHistoryRepository;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.entities.Portfolio;
import com.portfolio.backend.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final PortfolioHistoryRepository portfolioHistoryRepository;
    private final RequestService requestService;
    private final CoinRepository coinRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository, UserRepository userRepository,
                            PortfolioHistoryRepository portfolioHistoryRepository, RequestService requestService,
                            CoinRepository coinRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
        this.portfolioHistoryRepository = portfolioHistoryRepository;
        this.requestService = requestService;
        this.coinRepository = coinRepository;
    }


    public Portfolio createAndSavePortfolio(PortfolioDTO portfolioDTO, User user) {
//        public void createAndSavePortfolio(PortfolioDTO portfolioDTO, HttpSession session) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setDescription(portfolioDTO.getDescription());
        portfolio.setUser(user);
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    public List<Portfolio> getAllPortfoliosFor(Long id) {
        List<Portfolio> portfolios = new ArrayList<>();
        Iterable<Portfolio> allPortfolios = portfolioRepository.findAll();

        for (Portfolio p : allPortfolios) {
            if (p.getUser().getId() == id) {
                portfolios.add(p);
            }
        }
        return portfolios;
    }

    public Portfolio getPortfolioById(long id) {
        return portfolioRepository.findById(id);
    }

    public void saveCurrentPriceForAllPortfolios() throws IOException, JSONException {
        List<User> allUsers = (List<User>) userRepository.findAll();
        for (User user : allUsers) {
            List<Portfolio> portfolios = getAllPortfoliosFor(user.getId());
            for (Portfolio p : portfolios) {
                PortfolioHistory history = new PortfolioHistory();
                history.setTime(new Timestamp(System.currentTimeMillis()));
                history.setValue(requestService.getPriceForPortfolio(p.getId()));
                history.setPortfolio(p);
                portfolioHistoryRepository.save(history);
            }
        }
    }

    public Map<Long, List<String>> getPortfoliosNamesByUsername(String email) {

        List<Portfolio> portfolios = (List<Portfolio>) portfolioRepository.findAll();
        Map<Long, List<String>> portfolioNames = new HashMap<>();
        try {
            for (Portfolio portfolio : portfolios) {
                if (portfolio.getUser().getEmail().equals(email)) {
                    portfolioNames.put(portfolio.getId(), Arrays.asList(portfolio.getName(), portfolio.getDescription()));
                }
            }
            return portfolioNames;
        } catch (NullPointerException e) {
            return null;
        }

    }

    public Portfolio getPortfolioBy(String name) {
        return portfolioRepository.findByName(name);
    }

    public Map<String, String[]> getAllCoins(Portfolio portfolio) throws IOException, JSONException {

        List<Coin> allCoins = portfolio.getCoins();
        List<String> coinNames = new ArrayList<>();
        Map<String, Double> coinData = new HashMap<>();

        for (Coin coin : allCoins){
            if (!coinNames.contains(coin.getShortname())){
                coinNames.add(coin.getShortname());
            }
        }
        for (String name : coinNames) {
            for (Coin coin : allCoins) {
                if (coin.getShortname().equals(name)){
                    if (coinData.containsKey(name)){
                        coinData.replace(name, coinData.get(name) + coin.getAmount());
                    } else {
                        coinData.put(name, (double) coin.getAmount());
                    }
                }
            }
        }

        Map<String, String[]> coins = new HashMap<>();
        for (Coin coin : portfolio.getCoins()) {
            coins.put(coin.getLongname(),
                    new String[]{coin.getShortname(),
                            String.valueOf(coinData.get(coin.getShortname())),
                            String.valueOf(requestService.getPriceFor(coin)),
                            requestService.getValueChangeForCoin(coin)});
        }
        return coins;
    }

    @Transactional
    public void deletePortfolioById(Long id) {
        portfolioRepository.deleteById(id);
    }

    public Portfolio getPortfolioBy(Long id) {
        return portfolioRepository.findById(id);
    }

    public void deleteAll() {
        portfolioRepository.deleteAll();
    }
}
