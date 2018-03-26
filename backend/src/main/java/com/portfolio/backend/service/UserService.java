package com.portfolio.backend.service;

import com.portfolio.backend.DTO.UserDTO;
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


    public void createAndSaveUser(UserDTO userDTO) {
        User u = new User();
        u.setEmail(userDTO.getEmail());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(u);
    }

    public boolean validateUser(UserDTO userDTO) {
        try {

            User existing = userRepository.findByEmail(userDTO.getEmail());
            return passwordEncoder.matches(userDTO.getPassword(), existing.getPassword());
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Method to check, if current email is already in use.
     *
     * @param UserDTO User object.
     * @return True if email is in use. False if email is free to use.
     */
    public boolean isUserEmailExisting(UserDTO UserDTO) {
        User existing = userRepository.findByEmail(UserDTO.getEmail());
        return existing != null;
    }
}
