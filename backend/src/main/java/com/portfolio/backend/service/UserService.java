package com.portfolio.backend.service;

import com.portfolio.backend.pojos.UserFields;
import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void createAndSaveUser(UserFields userFields){
        User user = new User();
        user.setEmail(userFields.getEmail());
        user.setPassword(passwordEncoder.encode(userFields.getPassword()));
        userRepository.save(user);
    }

    public String validateUser(UserFields userFields){
        User existing = userRepository.findByEmail(userFields.getEmail());
        if (passwordEncoder.matches(userFields.getPassword(), existing.getPassword())) {
            return "Logged in!";
        } else {
            return "Invalid email or password!";
        }
    }
}
