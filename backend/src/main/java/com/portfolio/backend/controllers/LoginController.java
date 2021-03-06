package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> login(@RequestBody UserDTO userDTO, HttpSession session) throws IOException, JSONException {
        if (userService.validateUser(userDTO)) {
         // requestService.createAndUpdateCoinNames();
            Map<String, String> data = new HashMap<>();
            session.setAttribute("name", userDTO.getEmail());
            data.put("username", userDTO.getEmail());
            data.put("sessionid", session.getId());

            return data;
        } else {
            return null;
        }
    }
}
