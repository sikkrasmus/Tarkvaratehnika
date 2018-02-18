package com.portfolio.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

//@RestController
@org.springframework.stereotype.Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
