package com.goShopping.V2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    private String custom;
    private int quantity;
    private int priority;   //1=green, 2=yellow, 3=red

    public ListItem(int quantity, Product product, ShoppingList shoppingList) {
        this.quantity = quantity;
        this.product = product;
        this.shoppingList = shoppingList;
        priority = 1;
    }
    protected ListItem() {}
    public long getId() {
        return id;
    }
    public void changePriority(int priority) {
        if (priority >= 1 && priority <= 3) {
            this.priority = priority;
        }
    }
    public int getPriority() {
        return priority;
    }
    public ShoppingList getShoppingList() {
        return shoppingList;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
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
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
    public void removeSpecification() {
        specification = null;
    }
    public Specification getSpecification() {
        return specification;
    }
    public void setCustom(String custom) {this.custom=custom;}
    public String getCustom()
    {
        return custom;
    }

}
