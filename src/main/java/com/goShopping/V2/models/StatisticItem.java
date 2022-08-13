package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class StatisticItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="statistic_id")
    @JsonIgnore
    private Statistic statistic;

    protected StatisticItem() {}
    public StatisticItem(Product product, int quantity) {
        this.product=product;
        this.quantity=quantity;
    }

    public long getId() {return id;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
