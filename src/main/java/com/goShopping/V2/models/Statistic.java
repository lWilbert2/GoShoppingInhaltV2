package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "statistic", cascade = CascadeType.ALL)
    List<StatisticItem> statisticItems;
    public User getUser() {return user;}
    public void setUser(User user) {
        this.user = user;
    }
    public List <StatisticItem> getStatisticItemList() {
        return statisticItems;
    }
    public void addStatisticItemList(StatisticItem statisticItem) {
        statisticItems.add(statisticItem);
    }

}
