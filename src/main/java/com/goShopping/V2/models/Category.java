package com.goShopping.V2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String name;

  @ManyToMany(mappedBy = "categories")
  List <Product> productsOfCategory;
    protected Category() {

    }
   public Category(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
    public List <Product> getProductsOfCategory()
    {
        return productsOfCategory;
    }
}
