package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.service.UserService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) {
        if (!userService.isUserEmailExisting(userDTO)){
            return userService.createAndSaveUser(userDTO);
        }
        return null;
    }
}
