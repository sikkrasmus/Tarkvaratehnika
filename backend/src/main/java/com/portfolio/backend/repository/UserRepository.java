package com.portfolio.backend.repository;

import com.portfolio.backend.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}