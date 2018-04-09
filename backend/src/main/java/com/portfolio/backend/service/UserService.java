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
            User existing = userRepository.findByEmailContaining(userDTO.getEmail());
            System.out.println(existing.getEmail() + " " + existing.getPassword());
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
        User existing = userRepository.findByEmailContaining(UserDTO.getEmail());
        return existing != null;
    }

    public User findUser(String email) {
        System.out.println("email: " + email);
        User exist = userRepository.findByEmailContaining(email);
        System.out.println(exist);
        return userRepository.findByEmailContaining(email);
    }

    public User getUserBy(String email) {
        return userRepository.findByEmailContaining(email);
    }

    public User getUserBy(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserWith(String name) {
        userRepository.deleteByName(name);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
