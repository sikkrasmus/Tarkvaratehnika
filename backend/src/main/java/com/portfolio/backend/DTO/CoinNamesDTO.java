package com.portfolio.backend.DTO;

public class CoinNamesDTO {

    private String shortName;
    private String longname;

    public CoinNamesDTO() {
    }

    public CoinNamesDTO(String shortName, String longname) {
        this.shortName = shortName;
        this.longname = longname;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }
}
