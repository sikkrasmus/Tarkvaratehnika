package com.portfolio.backend.controllers;

import com.portfolio.backend.coins.APIFormat;
import com.portfolio.backend.pojos.UserFields;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/home")
    public String home() throws IOException, JSONException {
//        requestService.getMarketSummary();
        return "home";
    }

    @RequestMapping(path = "/index")
    public String index() {
        try {
            requestService.getMarketSummary();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return "index";
    }

    @RequestMapping(path = "/")
    public String landing() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<APIFormat> helloRest() {
        return requestService.getCoinRepository().getApiFormatList();
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