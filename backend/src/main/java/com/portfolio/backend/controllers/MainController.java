package com.portfolio.backend.controllers;

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
    String register(@RequestParam String email,
                       @RequestParam String password) {
        User n = new User();
        System.out.println(email);
        System.out.println(password);
        n.setEmail(email);
        n.setPassword(passwordEncoder.encode(password));
        userRepository.save(n);
        return "User created";
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
}