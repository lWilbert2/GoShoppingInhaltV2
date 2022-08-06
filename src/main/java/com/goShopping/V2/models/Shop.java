package com.goShopping.V2.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Shop {
    protected Shop() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String adress;
    //private Datentyp Koordinaten;     //fuer googlemap koordinaten

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> inventory;

    public Shop(String name, String adress){
        this.name = name;
        this.adress = adress;
    }
    public Shop(String name, String adress, ArrayList<Product> inventory){
        this.name = name;
        this.adress = adress;
        this.inventory = inventory;
    }
    public void addProduct(Product product)
    {
        inventory.add(product);
    }
    public List <Product> getProducts() {
        return inventory;
    }

    public boolean hasProduct(Product product)
    {
        for(Product p: inventory)
        {
            if(p==product)
            {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> inStock(ArrayList<Product> shoppingList){    //checkt die gesamte Liste. und returnt eine Liste mit allen Produkten die im Laden sind.
        ArrayList<Product> result = new ArrayList();

        for (Product p: shoppingList) {
            String productName = p.getName();
            int tmp = binarySearch(productName);

            if(tmp == -1){
                continue;
            }
            result.add(p);
        }
        //geeigneter suchalgorithmus im  inventory. Entweder index des Produktes = ihrer ID oder alphabethisch sortiertes Inventory.


        return result;
    }
    public boolean isProductInStock(Product product){  //checkt ob einzelnes Product der Einkaufsliste auf Lager ist

        long id = product.getId();
        String name = product.getName();
        //dummer algorithmus
        for(Product p : this.inventory){
            if(p.getId() == id){
                return true;
            }
            else if(p.getName().equals(name)){
                return true;
            }
        }
        return false;

        //BESSER WÄRE

        //Sortiertes Inventory wie in Methode hierrüber beschrieben

    }

    public List<Product> getInventory() {
        return inventory;
    }
    public List<Product> getInventorybyCategory(){ //überlegen wie die Kategory vom Frontend übergeben wird. über String, boolean oder Zahlentyp??

        ArrayList<Product> result = new ArrayList<>();
        return result;
    }

    public int binarySearch(String searchedProduct){
        int low = 0;
        int high = inventory.size() - 1;

        while(low <= high){
            int mid = low + (high - low)/2;
            String malcom = inventory.get(mid).getName();

            if(searchedProduct.compareTo(malcom) == 0){
                return mid;
            }
            else if(searchedProduct.compareTo(malcom) > 0){
                low = mid + 1;
            }
            else{
                high = mid -1;
            }
        }

        return -1;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Product> subInventoryByCategory(boolean[] categorys){
        return new ArrayList<>();
    }

}
