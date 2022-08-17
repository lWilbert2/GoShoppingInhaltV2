package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/{id}/{itemId}/addAmount/{amountId}")
    //Fügt Menge aus der Liste selbst hinzu
    public void addAmount(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("amountId") long amountId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
    }
    @GetMapping("/{id}/{itemId}/removeAmount")
    //entfernt Amount aus der Liste aus, von der Liste
    public void removeAmount(@PathVariable("itemId") long itemId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeAmount();
        listItemRepository.save(listItem);
    }
    @GetMapping("/{id}/{itemId}/removeSpecification")
    //entfernt Spezifikation aus der Liste aus, von der Liste
    public void removeSpecification(@PathVariable("itemId") long itemId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeSpecification();
        listItemRepository.save(listItem);
    }
    @GetMapping("/{id}/{itemId}/addSpecification/{specificationId}")
    //Fügt Spezifikation aus der Liste selbst hinzu
    public void addSpecification(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("specificationId") long specificationId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
    }
    @GetMapping("/{id}/{itemId}/removeCostum")
    public void removeCostum(@PathVariable("itemId") long itemId) {
        ListItem listitem=listItemRepository.findById(itemId).get();
        listitem.setCostum(null);
        listItemRepository.save(listitem);
    }

    //Controller auf Alle Produkte

    @GetMapping("/{id}/products/{productId}/addSpeciFromProduct/{specificationId}")
    @ResponseBody
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addSpecificationfromProduct(@PathVariable("id") long id, @PathVariable("productId") long productId, @PathVariable("specificationId") long specificationId) {
        Product product = productRepo.findById(productId).get();
        ListItem listItem;
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                if(li.getSpecification()==specificationRepository.findById(specificationId).get())
                {
                    li.setSpecification(null);
                    listItemRepository.save(li);
                }
                else {
                    li.setSpecification(specificationRepository.findById(specificationId).get());
                    listItemRepository.save(li);
                }
                return li;
            }
        }
        listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(id).get());
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
        return listItem;
    }

    @GetMapping("/{listId}/products/{productId}/addAmountFromProduct/{amountId}")
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addAmountFromProduct(@PathVariable("listId") long listId, @PathVariable("productId") long productId, @PathVariable("amountId") long amountId) {
        List<ListItem> listItems = shoppingListRepo.findById(listId).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                if(li.getAmount()==amountRepository.findById(amountId).get())
                {
                    li.setAmount(null);
                    listItemRepository.save(li);
                }
                else {
                    li.setAmount(amountRepository.findById(amountId).get());
                    listItemRepository.save(li);
                }
                return li;
            }
        }
        ListItem listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
        return listItem;
    }
    @GetMapping("/{id}/products/{productId}/removeCostum")
    public void removeCostumFromProducts(@PathVariable("id") long id, @PathVariable("productId") long productId) {
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                li.setCostum(null);
                listItemRepository.save(li);
            }

        }
    }
}
