package com.portfolio.backend.DTO;

public class UserPortfolioDTO {

    private String portfolioName;
    private String description;
    private String email;

    public UserPortfolioDTO() {
    }

    public UserPortfolioDTO(String portfolioName, String description, String email) {
        this.portfolioName = portfolioName;
        this.description = description;
        this.email = email;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }
}
