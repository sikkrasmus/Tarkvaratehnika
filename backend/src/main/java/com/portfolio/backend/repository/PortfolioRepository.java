package com.portfolio.backend.repository;

import com.portfolio.backend.entities.Portfolio;
import org.springframework.data.repository.CrudRepository;

import javax.sound.sampled.Port;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
    Portfolio findById(Long id);
    Portfolio findByName(String name);
    Portfolio findByUserId(Long id);
    void deleteByName(String name);
}
