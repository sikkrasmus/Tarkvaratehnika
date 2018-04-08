package com.portfolio.backend.DTO;

import java.sql.Timestamp;

public class PortfolioHistoryDTO {

    private Long portfolioID;
//    private Timestamp time;
    private int value;

    public PortfolioHistoryDTO() {
    }

    public PortfolioHistoryDTO(Long portfolioID, int value) {
        this.portfolioID = portfolioID;
        this.value = value;
    }

    public Long getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Long portfolioID) {
        this.portfolioID = portfolioID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
