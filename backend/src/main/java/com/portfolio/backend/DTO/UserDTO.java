package com.portfolio.backend.DTO;

import com.portfolio.backend.validations.ValidEmail;
import com.portfolio.backend.validations.ValidPassword;


public class UserDTO {

    @ValidEmail
    private String email;
    @ValidPassword
    private String password;

    private String name;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}