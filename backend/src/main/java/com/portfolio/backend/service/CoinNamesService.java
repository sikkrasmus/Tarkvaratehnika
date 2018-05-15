package com.portfolio.backend.service;

import com.portfolio.backend.DTO.CoinNamesDTO;
import com.portfolio.backend.entities.CoinNames;
import com.portfolio.backend.repository.CoinNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinNamesService {

    private final CoinNamesRepository coinNamesRepository;

    @Autowired
    public CoinNamesService(CoinNamesRepository coinNamesRepository) {
        this.coinNamesRepository = coinNamesRepository;
    }

    public void createAndSaveCoin(CoinNamesDTO dto) {
        CoinNames coinNames = new CoinNames();
        coinNames.setLongname(dto.getLongname());
        coinNames.setShortname(dto.getShortName());
        coinNamesRepository.save(coinNames);
    }

    public CoinNames getCoinNamesBy(String shortName) {
        return coinNamesRepository.findTopByShortname(shortName);
    }

    public void deleteAll() {
        coinNamesRepository.deleteAll();
    }
}
