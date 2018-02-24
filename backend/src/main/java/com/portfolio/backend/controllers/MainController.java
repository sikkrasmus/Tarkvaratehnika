package com.portfolio.backend.controllers;

import com.portfolio.backend.pojos.UserRegistration;
import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }
//
//    @RequestMapping(path = "/login")
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping(path = "/home")
//    public String home() {
//        return "home";
//    }



    @PostMapping(value="/register")
    public @ResponseBody
    String register(@RequestBody UserRegistration userRegistration) {
        User n = new User();
        System.out.println(userRegistration.getEmail());
        System.out.println(userRegistration.getPassword());
        n.setEmail(userRegistration.getEmail());
        n.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userRepository.save(n);
        return "User created";
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
}