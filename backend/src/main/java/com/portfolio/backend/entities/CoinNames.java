package com.portfolio.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "Coinnames")
public class CoinNames {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coin_id")
    private long id;

    private String shortname;
    private String longname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }
}
