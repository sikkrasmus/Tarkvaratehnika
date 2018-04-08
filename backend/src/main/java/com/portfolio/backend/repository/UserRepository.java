package com.portfolio.backend.repository;

import com.portfolio.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findById(Long id);
}