package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String herkunftsland;

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

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ListItem> listItems;
    protected Product() {
    }
    public Product(String name) {
        this.name = name;
    }
    public void addCategory(Category category)
    {
        categoriesOfProducts.add(category);
    }
    public void removeCategory(Category category)
    {
        categoriesOfProducts.remove(category);
    }
    public List <Category> getCategories()
    {
        return categoriesOfProducts;
    }
    public void addSpecification(Specification specification) {
        specifications.add(specification);
    }
    public void removeSpecification(Specification specification) {specifications.remove(specification);}
    public List <Specification> getSpecifications() {return specifications;}
    public List <Amount> getAmounts(){return amounts;}
    public void addAmount(Amount amount) {
        amounts.add(amount);
    }
    public void removeAmount(Amount amount) {amounts.remove(amount);}
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
        return id;
    }
    public List<ListItem> getListItems() {
        return listItems;
    }
    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }
}
