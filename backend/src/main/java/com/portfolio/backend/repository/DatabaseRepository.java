package com.portfolio.backend.repository;

import com.portfolio.backend.user.Database;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface DatabaseRepository extends CrudRepository<Database, Long>{
    Database findBySessionId(String sessionId);
    Database findById(Long id);
}
