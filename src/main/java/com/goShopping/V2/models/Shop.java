package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Shop {
    protected Shop() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    //private Datentyp Koordinaten;     //fuer googlemap koordinaten

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_item_id"))
    private List<ShopItem> inventory;

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Shop(String name, String address, ArrayList<ShopItem> inventory) {
        this.name = name;
        this.address = address;
        this.inventory = inventory;
    }

    public void addShopItem(ShopItem product) {
        inventory.add(product);
    }

    public List<ShopItem> getInventory() {
        return inventory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInventory(List<ShopItem> inventory) {
        this.inventory = inventory;
    }
}


