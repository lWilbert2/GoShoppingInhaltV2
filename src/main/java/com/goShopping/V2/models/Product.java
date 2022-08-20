package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String herkunftsland;
   // @ElementCollection
   // private List <String> specifications;

    @ManyToMany(mappedBy = "inventory")
    @JsonIgnore
    List <Shop> shops;

    @ManyToMany(mappedBy = "seasonalProducts")
    @JsonIgnore
    List <Month> seasonal;

    @ManyToMany(mappedBy = "productsOfCategory")
    @JsonIgnore
    List <Category> categoriesOfProducts;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id"))
    List <Specification> specifications;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "amount_id"))
    List <Amount> amounts;


    public Product(String name, String herkunftsland) {
        this.name = name;
        this.herkunftsland = herkunftsland;
        //specifications=new ArrayList<String>();
    }

    public Product(String name, String herkunftsland, List <String> specifications) {
        this.name = name;
        this.herkunftsland = herkunftsland;
        //this.specifications=specifications;
    }

    protected Product() {
    }
    //public void setSpecifications(List <String> specifications) {this.specifications=specifications;}
    public void addCategory(Category category)
    {
        categoriesOfProducts.add(category);
    }
    public void deleteCategory(Category category)
    {
        categoriesOfProducts.remove(category);
    }
    public List <Category> getCategories()
    {
        return categoriesOfProducts;
    }
    public List <Specification> getSpecifications()
    {
        return specifications;
    }
    public List <Amount> getAmounts(){return amounts;}
    public void addSpecification(Specification specification) {
        specifications.add(specification);
    }
    public void addAmount(Amount amount) {
        amounts.add(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerkunftsland() {
        return herkunftsland;
    }

    public void setHerkunftsland(String herkunftsland) {
        this.herkunftsland = herkunftsland;
    }

    public long getId() {
        return Id;
    }
}
