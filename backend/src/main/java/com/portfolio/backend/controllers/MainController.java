package com.portfolio.backend.controllers;

import com.portfolio.backend.repository.UserRepository;
import com.portfolio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping(path = "/register")
    public String register() {
        return "register.html";
    }



    @GetMapping(path="/register") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser (@RequestParam String email,
                       @RequestParam String password) {
        User n = new User();
        n.setEmail(email);
        n.setPassword(passwordEncoder.encode(password));
        userRepository.save(n);
        return "Index.html";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}