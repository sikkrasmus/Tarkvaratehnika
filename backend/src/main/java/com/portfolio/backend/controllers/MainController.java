package com.portfolio.backend.controllers;

import com.portfolio.backend.pojos.UserFields;
import com.portfolio.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(path = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/")
    public String landing() {
        return "index";
    }


    @PostMapping(value="/register")
    public @ResponseBody
    String register(@RequestBody UserFields userFields) {
        userService.createAndSaveUser(userFields);
        return "User created";
    }

    @PostMapping(value="/login")
    public @ResponseBody
    String login(@RequestBody UserFields userFields) {
        return userService.validateUser(userFields);
    }
}