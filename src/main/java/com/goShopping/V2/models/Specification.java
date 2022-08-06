package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Specification {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    @ManyToMany(mappedBy = "specifications")
    List<Product> productsWithSpecification;

    @OneToMany(mappedBy = "specification", cascade = CascadeType.ALL)
    private List<ListItem> list;

    private String name;
    public Specification(String name) {
        this.name=name;
    }
    public Specification() {}
    public long getId() {return Id;}
    public String getName() {
        return name;
    }
    public String setName()
    {
        return name;
    }
    public List<Product> getProductsOfSpecification() {
        return productsWithSpecification;
    }
}
