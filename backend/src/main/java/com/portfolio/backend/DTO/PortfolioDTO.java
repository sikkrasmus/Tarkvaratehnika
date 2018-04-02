package com.portfolio.backend.DTO;

import javax.validation.constraints.NotNull;

public class PortfolioDTO {

    @NotNull(message = "Name cannot be EMPTY!")
    private String name;

    private String description;

    public PortfolioDTO() {
    }

    public PortfolioDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
