package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Seasonal")
public class Month {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    private String name;

    @ManyToMany @JoinTable(
            joinColumns = @JoinColumn(name = "month_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List <Product> seasonalProducts;


    protected Month() {}
    public Month(String name)
    {
        seasonalProducts=new ArrayList<Product>();
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void addProduct(Product product)
    {
        seasonalProducts.add(product);
    }
    public List <Product> getProducts()
    {
        return seasonalProducts;
    }
    public void setName(String name) {
        this.name = name;
    }
}

