package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_id")
public class User {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List <ShoppingList> shoppingLists;

    @OneToOne
    private Statistic statistics;

    public User(String name, Statistic statistics)
    {
        this.name=name;
        this.statistics=statistics;
    }
    protected User() {}

    public long getId() {return id;}
    public List<ShoppingList> getShoppingLists() {return shoppingLists;}
    public void setShoppingLists(List<ShoppingList> shoppingLists) {this.shoppingLists = shoppingLists;}
    public void addShoppingList(ShoppingList shoppingList){shoppingLists.add(shoppingList);}

    public Statistic getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistic statistics) {
        this.statistics = statistics;
    }
}
