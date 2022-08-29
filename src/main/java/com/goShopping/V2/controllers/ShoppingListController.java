package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//Operationen auf der Shoppinglist, Alle Funktionen werden aus dem Frontend verwendet

@RestController
@RequestMapping("users/{userId}/lists")
public class ShoppingListController {
    @Autowired
    private ShoppingListRepository shoppingListRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatisticItemRepository statisticItemRepository;

    @GetMapping("/{listId}/delete")
    //Lösche Liste mit id
    public void deleteList(@PathVariable("listId") Long listId) {
        shoppingListRepo.deleteById(listId);
    }


    //Operationen auf Products auf Liste

    @GetMapping("/{listId}/products/{productId}/add")
    //Fügt Produkt der Liste hinzu
    public int addProductToList(@PathVariable("productId") long productId, @PathVariable("listId") long listId) {
        ShoppingList s = shoppingListRepo.findById(listId).get();
        s.addProduct(productRepo.findById(productId).get());
        shoppingListRepo.save(s);
        return listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId).getQuantity();
    }
    @GetMapping("/{listId}/products/{productId}/delete")
    //Lösche Produkt, bzw verringere die Anzahl über - auf Template
    public int DeleteProductfromList(@PathVariable("productId") long productId, @PathVariable("listId") long listId) {
        ListItem listItem = listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
        if (listItem.getQuantity() > 0) {
            listItem.setQuantity(listItem.getQuantity() - 1);
            if (listItem.getQuantity() == 0) {
                listItemRepository.deleteById(listItem.getId());
                return 0;
            } else {
                listItemRepository.save(listItem);
                return listItem.getQuantity();
            }
        }
        return 0;
    }
    @GetMapping("/{listId}/products/{productId}/quantity")
    //Gibt die Anzahl des Jeweiligen Produktes auf der Liste zurück
    public int getQuantity(@PathVariable("listId") long listId, @PathVariable("productId") long productId) {
        if(listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId)!=null)
        {
            return listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId).getQuantity();
        }
        else return 0;

    }

    //Gibt ListItems der Jeweiligen Liste, des angefragten Produktes zurück
    @GetMapping("/{listId}/products/{productId}/items")
    public ListItem getItemfromProduct(@PathVariable("listId") long listId, @PathVariable("productId") long productId) {

        return listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
    }

    @GetMapping("/{listId}/items/{itemId}/delete")
    //Lösche Items (nicht Anzahl verändern)
    public void deleteItemfromList(@PathVariable("listId") long listId, @PathVariable("itemId") long itemId) {
        listItemRepository.deleteById(itemId);
    }

    @GetMapping("/{listId}/items/{itemId}/buy/{quantity}")
    //"Kauft" Item --> Item wird von der Liste gelöscht, aber in der Statistic gespeichert.
    public void buyItem(@PathVariable("userId") long userId, @PathVariable("listId") long listId, @PathVariable("itemId") long itemId, @PathVariable("quantity") int quantity) {
        Product product = listItemRepository.findById(itemId).get().getProduct();
        Statistic statistic = userRepository.findById(userId).get().getStatistics();
        List<StatisticItem> statisticItemList = statistic.getStatisticItemList();

        for (StatisticItem statisticItem : statisticItemList) {
            if (statisticItem.getProduct() == product) {
                statisticItem.setQuantity(statisticItem.getQuantity() + quantity);
                statisticItem.setStatistic(statistic);
                statisticItemRepository.save(statisticItem);
                listItemRepository.deleteById(itemId);
                return;
            }
        }
        StatisticItem statisticItem = new StatisticItem(product, quantity);
        statisticItem.setStatistic(statistic);
        statisticItemRepository.save(statisticItem);
        listItemRepository.deleteById(itemId);
        return;
    }
    @GetMapping("/{listId}/items/{itemId}/priority/{value}")
    //Ändert die Priorität, 3=rot --> Sehr wichtig, 2=gelb --> wichtig, 1=grün --> noch nicht allzu wichtig // Sinnvoll für WGS
    public List<ListItem> sortByPriority(@PathVariable("listId") long listId, @PathVariable("itemId") long itemId, @PathVariable("value") int value) {
        ShoppingList shoppingList = shoppingListRepo.findById(listId).get();
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.changePriority(value);
        listItemRepository.save(listItem);
        return shoppingList.getList();
    }

    @GetMapping("/{listId}/shops/{shopId}/check")
    //CheckStores Welche(und wie viele) Produkte haben die jeweiligen Shops von der Liste
    public List<ShopItem> CheckShop(@PathVariable("listId") long listId, @PathVariable("shopId") long shopId) {
        List<ListItem> OnList = shoppingListRepo.findById(listId).get().getList();
        List<ShopItem> FoundProductsInShop = new ArrayList<ShopItem>();
        List<ShopItem> AllItemsInShop = shopRepository.findById(shopId).get().getInventory();
        for (ListItem listItem : OnList) {
            for (ShopItem shopItem : AllItemsInShop) {
                if (listItem.getProduct().getId() == shopItem.getProduct().getId()) {
                    FoundProductsInShop.add(shopItem);
                    break;
                }
            }
        }
        return FoundProductsInShop;
    }
}
