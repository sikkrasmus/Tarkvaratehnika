package com.portfolio.backend.controllers;

import com.portfolio.backend.pojos.UserFields;
import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }
//
    @RequestMapping(path = "/home")
    public String home() {
        return "home";
    }



    @PostMapping(value="/register")
    public @ResponseBody
    String register(@RequestBody UserFields userFields) {
        User n = new User();
        n.setEmail(userFields.getEmail());
        n.setPassword(passwordEncoder.encode(userFields.getPassword()));
        userRepository.save(n);
        return "User created";
    }

    @PostMapping(value="/login")
    public @ResponseBody
    String login(@RequestBody UserFields userFields) {
        User existing = userRepository.findByEmail(userFields.getEmail());
        if (passwordEncoder.matches(userFields.getPassword(), existing.getPassword())) {
            System.out.println("logged in as: " + existing.getEmail());
            return "Logged in";
        } else {
            System.out.println("Invalid email or password");
            return "Invalid email or password";
        }
    }
}