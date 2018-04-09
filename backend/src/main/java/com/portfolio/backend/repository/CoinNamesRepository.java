package com.portfolio.backend.repository;

import com.portfolio.backend.entities.CoinNames;
import org.springframework.data.repository.CrudRepository;

public interface CoinNamesRepository extends CrudRepository<CoinNames, Long> {
    CoinNames findById(long id);
    CoinNames findByShortname(String shortName);
    CoinNames findByLongname(String longname);
}
