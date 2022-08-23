package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//Alle Operationen die Specifications, Amounts oder custom Specifications betreffen, genutzt vom Frontend
@RestController
@RequestMapping("users/{userId}/lists")
public class SpecificationAndAmountController {
    @Autowired
    private ShoppingListRepository shoppingListRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private AmountRepository amountRepository;
    @Autowired
    private SpecificationRepository specificationRepository;


    //Controller auf ProductsOnList
    @GetMapping("/{listId}/items/{itemId}/amounts/{amountId}/add")
    //Fügt Menge aus der Liste selbst hinzu
    public void addAmount(@PathVariable("itemId") long itemId, @PathVariable("amountId") long amountId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
    }

    @GetMapping("/{listId}/items/{itemId}/amounts/remove")
    //entfernt Amount aus der Liste aus, von der Liste
    public void removeAmount(@PathVariable("itemId") long itemId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeAmount();
        listItemRepository.save(listItem);
    }

    @GetMapping("/{listId}/items/{itemId}/specifications/remove")
    //entfernt Spezifikation aus der Liste aus, von der Liste
    public void removeSpecification(@PathVariable("itemId") long itemId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeSpecification();
        listItemRepository.save(listItem);
    }

    @GetMapping("/{listId}/items/{itemId}/specifications/{specificationId}/add")
    //Fügt Spezifikation aus der Liste selbst hinzu
    public void addSpecification(@PathVariable("itemId") long itemId, @PathVariable("specificationId") long specificationId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
    }

    @GetMapping("/{listId}/items/{itemId}/custom/remove")
    public void removeCostum(@PathVariable("itemId") long itemId) {
        ListItem listitem = listItemRepository.findById(itemId).get();
        listitem.setCustom(null);
        listItemRepository.save(listitem);
    }

    //Controller auf Alle Produkte
    @GetMapping("/{listId}/products/{productId}/specifications/{specificationId}/set")
    @ResponseBody
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addSpecificationfromProduct(@PathVariable("listId") long listId, @PathVariable("productId") long productId, @PathVariable("specificationId") long specificationId) {
        if(listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId)!=null) {
            ListItem listItem=listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
                if (listItem.getSpecification() == specificationRepository.findById(specificationId).get()) {
                    listItem.setSpecification(null);
                    listItemRepository.save(listItem);
                } else {
                    listItem.setSpecification(specificationRepository.findById(specificationId).get());
                    listItemRepository.save(listItem);
                }
                return listItem;
            }
       ListItem listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
        return listItem;
    }

    @GetMapping("/{listId}/products/{productId}/amounts/{amountId}/set")
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addAmountFromProduct(@PathVariable("listId") long listId, @PathVariable("productId") long productId, @PathVariable("amountId") long amountId) {
        if(listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId)!=null) {
            ListItem listItem=listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
            if (listItem.getAmount() == amountRepository.findById(amountId).get()) {
                listItem.setAmount(null);
                listItemRepository.save(listItem);
            } else {
                listItem.setAmount(amountRepository.findById(amountId).get());
                listItemRepository.save(listItem);
            }
            return listItem;
        }
        ListItem listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
        return listItem;
}

    @GetMapping("/{listId}/products/{productId}/custom/remove")
    public void removeCustomFromProducts(@PathVariable("listId") long listId, @PathVariable("productId") long productId) {
        ListItem listItem = listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
        listItem.setCustom(null);
        listItemRepository.save(listItem);
    }

    @GetMapping("/{listId}/products/{productId}/custom/{custom}/add")
    public void addCustomFromProducts(@PathVariable("listId") long listId, @PathVariable("productId") long productId, @PathVariable("custom") String custom) {
        ListItem listItem = listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
        listItem.setCustom(custom);
        listItemRepository.save(listItem);
    }
}
