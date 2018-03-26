package com.portfolio.backend.repository;

import com.portfolio.backend.user.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
    Portfolio findByName(String name);
}
