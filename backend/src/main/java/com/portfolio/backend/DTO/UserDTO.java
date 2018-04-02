package com.portfolio.backend.DTO;

import com.portfolio.backend.validations.ValidEmail;
import com.portfolio.backend.validations.ValidPassword;


public class UserDTO {

    @ValidEmail
    private String email;
    @ValidPassword
    private String password;

    private Long id;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

    public Long getId() {
        return id;
    }
}