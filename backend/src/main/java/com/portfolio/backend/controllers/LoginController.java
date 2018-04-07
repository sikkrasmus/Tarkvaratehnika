package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO login(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getEmail() + " " + userDTO.getPassword());
        if (userService.validateUser(userDTO)){
            return userDTO;
        } else {
            return null;
        }
    }
}
