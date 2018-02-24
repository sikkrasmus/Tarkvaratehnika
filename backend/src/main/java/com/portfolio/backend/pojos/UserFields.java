package com.portfolio.backend.pojos;

public class UserFields {

    private String email;
    private String password;

    public UserFields() {
    }

    public UserFields(String email, String password) {
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
}