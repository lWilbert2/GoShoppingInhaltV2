package com.goShopping.V2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List <Product> productsOfCategory;
    protected Category() {

    }
   public Category(String name)
    {
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }
    public List <Product> getProductsOfCategory()
    {
        return productsOfCategory;
    }
    public void addProduct(Product product)
    {
        productsOfCategory.add(product);
    }
    public void removeProduct(Product product)
    {
        productsOfCategory.remove(product);
    }
}
