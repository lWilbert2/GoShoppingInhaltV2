package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class StatisticShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private Shop shop;
    private int quantity;
    private StatisticShop(Shop shop, int quantity) {
        this.shop=shop;
        this.quantity=quantity;
    }
    protected StatisticShop() {}
    public Shop getShop() {return shop;}
    public void setShop(Shop shop) {this.shop = shop;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

}
