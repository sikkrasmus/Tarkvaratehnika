package com.portfolio.backend.service;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO createAndSaveUser(UserDTO userDTO) {
        User u = new User();
        u.setEmail(userDTO.getEmail());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(u);
        return userDTO;
    }

    public boolean validateUser(UserDTO userDTO) {
        try {
            User existing = userRepository.findByEmail(userDTO.getEmail());
            System.out.println(existing.getEmail() + " " + existing.getPassword());
            return passwordEncoder.matches(userDTO.getPassword(), existing.getPassword());
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isUserEmailExisting(UserDTO UserDTO) {
        User existing = userRepository.findByEmail(UserDTO.getEmail());
        return existing != null;
    }

    public User getUserBy(long id) {
        return userRepository.findById(id);
    }

    public User getUserBy(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserWith(String name) {
        userRepository.deleteByName(name);
    }
}
