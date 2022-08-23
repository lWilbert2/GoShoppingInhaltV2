package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<ListItem> list;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String name;

    public ShoppingList(String name) {
        this.name = name;
        list = new ArrayList<ListItem>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ShoppingList() {
    }

    public List<ListItem> getList() {
        return list;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<ListItem> list) {
        this.list = list;
    }

    public void addProduct(Product p) {
        int c = checkProduct(p);
        if (c == -1) {
            ListItem i = new ListItem(1, p, this);
            list.add(i);
        } else {
            int quant = list.get(c).getQuantity();
            list.get(c).setQuantity(quant + 1);
        }
    }

    private int checkProduct(Product p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct().getId() == p.getId()) {
                return i;
            }
        }
        return -1;
    }
}
