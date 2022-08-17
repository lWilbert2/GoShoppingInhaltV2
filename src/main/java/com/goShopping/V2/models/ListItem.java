package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.List;

@Entity
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @JsonIgnore
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "specification_id")
    Specification specification;

    @ManyToOne
    @JoinColumn(name = "amount_id")
    Amount amount;

    private String costum;
    private int quantity;
    private int priority;   //1=green, 2=yellow, 3=red

    public ListItem(int quantity, Product product, ShoppingList shoppingList) {
        this.quantity = quantity;
        this.product = product;
        this.shoppingList = shoppingList;
        priority = 1;
    }

    protected ListItem() {
    }
    public void setCostum(String costum)
    {
        this.costum=costum;
    }
    public String getCostum()
    {
        return costum;
    }

    public void changePriority(int priority) {
        if (priority >= 1 && priority <= 3) {
            this.priority = priority;
        }
    }

    public void changeId(long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;

        /*if(priority==1) {return "green";}
        else if(priority==2) {return "yellow";}
        else {return "red";}*/
    }

    public int getPriorityInt() {
        return priority;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setP(Product p) {
        this.product = p;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public void removeAmount() {
        this.amount = null;
    }

    public Amount getAmount() {
        return amount;
    }

    public void removeSpecification() {
        specification = null;
    }

    public Specification getSpecification() {
        return specification;
    }
}
