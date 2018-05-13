package com.portfolio.backend.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Portfolio")
public class Portfolio {
    @Id
    @GeneratedValue
    @Column(name = "portfolio_id")
    private long id;

//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "userId")
//    private long userId;


//    @Column(name = "email")
//    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio", fetch = FetchType.LAZY)
    private List<Coin> coins = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio", fetch = FetchType.LAZY)
    private Set<PortfolioHistory> histories = new HashSet<>();

    public Set<PortfolioHistory> getHistories() {
        return histories;
    }

    public void setHistories(Set<PortfolioHistory> histories) {
        this.histories = histories;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String name;
    private String description;


//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

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