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
    /*@OneToMany(mappedBy = "statistic", cascade = CascadeType.ALL)
    List<StatisticShop> statisticShops;*/
  //  public List<StatisticShop> getStatisticShops() {return statisticShops;}
    //public void setStatisticShops(List<StatisticShop> statisticShops) {this.statisticShops = statisticShops;}
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
