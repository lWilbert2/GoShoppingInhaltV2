package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Amount {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    @ManyToMany(mappedBy = "amounts")
    List<Product> productsWithAmount;

    @OneToMany(mappedBy = "amount", cascade = CascadeType.ALL)
    private List<ListItem> list;

    private String name;
    public Amount(String name) {
        this.name=name;
    }
    public Amount() {}
    public long getId() {return Id;}
    public String getName() {
        return name;
    }
    public String setName()
    {
        return name;
    }
    public List<Product> getProductsOfSpecification() {
        return productsWithAmount;
    }
}
