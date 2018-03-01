package com.portfolio.backend.service;

import com.portfolio.backend.pojos.UserDTO;
import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void createAndSaveUser(UserDTO userDTO){
        User u = new User();
        u.setEmail(userDTO.getEmail());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(u);
    }

    public String validateUser(UserDTO userDTO){
        User existing = userRepository.findByEmail(userDTO.getEmail());
        if (passwordEncoder.matches(userDTO.getPassword(), existing.getPassword())) {
            return "Logged in!";
        } else {
            return "Invalid email or password!";
        }
    }
}
