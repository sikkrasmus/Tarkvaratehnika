package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.coins.APIFormat;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(path = "/register")
    public String register(UserDTO userDTO) {
        return "register";
    }

    @GetMapping(path = "/login")
    public String login(UserDTO userDTO) {
        return "login";
    }

    @RequestMapping(path = "/home")
    public String home(HttpSession session) throws IOException, JSONException {
//        requestService.getMarketSummary();
        if (!session.isNew()){
            return "home";
        }
        return "login";
    }

    @RequestMapping(path = "/addPortfolio")
    public String addPortfolio(PortfolioDTO portfolioDTO) {
        return "addPortfolio";
    }
    @RequestMapping(path = "/portfolioView")
    public String portfolioView() {
        return "portfolioView";
    }

    @RequestMapping(path = "/index")
    public String index(HttpSession session) {
        try {
            requestService.getMarketSummary();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        session.invalidate();
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

    @PostMapping(value = "/register")
    public String registerValidation(@Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!userService.isUserEmailExisting(userDTO)) {
            userService.createAndSaveUser(userDTO);
            return "redirect:/login";
        }
        return "register";
    }

    @PostMapping(value = "/login")
    public String login(@Valid UserDTO userDTO, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (userService.validateUser(userDTO)) {
            session.setAttribute("name", userDTO.getEmail());
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping(value = "/addPortfolio")
    public String addPortfolio(@Valid PortfolioDTO portfolioDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPortfolio";
        }
        portfolioService.createAndSavePortfolio(portfolioDTO, session);
        return "redirect:/home";
    }


    @ModelAttribute(name = "portfolios")
    public List<String> portfolios(HttpSession session) {
        try {

            return portfolioService.getAllUserPortfolios(session.getAttribute("name").toString());
        } catch (NullPointerException e) {
            return null;
        }
    }

}