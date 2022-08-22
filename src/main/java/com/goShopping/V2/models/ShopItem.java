package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity

public class ShopItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private Shop shop;

    int position;
    protected ShopItem() {}
    public ShopItem(Shop shop, Product product, int position)
    {
        this.shop=shop;
        this.product=product;
        this.position=position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
