package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SpecificationRepository specificationRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AmountRepository amountRepository;
    @Autowired
    private StatisticItemRepository statisticItemRepository;

    @GetMapping("/{id}/delete")
    //Lösche Liste mit id {id}
    //Funktioniert!
    public void deleteList(@PathVariable("id") Long id) {
        shoppingListRepo.deleteById(id);
    }

    @GetMapping("/{id}/addProduct/{productId}")
    //Fügt Produkt der Liste hinzu
    //Funktioniert!
    public int addProductToList(@PathVariable("productId") long productId, @PathVariable("id") long listId) {
        ShoppingList s = shoppingListRepo.findById(listId).get();
        s.addProduct(productRepo.findById(productId).get());
        shoppingListRepo.save(s);
        List<ListItem> listItems = s.getList();
        for (ListItem listItem : listItems) {
            if (listItem.getProduct().getId() == productId) {
                return listItem.getQuantity();
            }
        }
        return 0;
    }

    @GetMapping("/{id}/products/{productId}/getQuantity")
    //Gibt die Anzahl des Jeweiligen Produktes auf der Liste zurück
    //Funktioniert!
    public int getQuantity(@PathVariable("id") long id, @PathVariable("productId") long productId) {
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                return li.getQuantity();
            }
        }
        return 0;
    }
    @GetMapping("/{id}/products/{productId}/getItem")
    //Gibt die Anzahl des Jeweiligen Produktes auf der Liste zurück
    //Funktioniert!
    public ListItem getItemfromProduct(@PathVariable("id") long id, @PathVariable("productId") long productId) {
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                return li;
            }
        }
        return null;
    }

    @GetMapping("/{id}/deleteProduct/{productId}")
    //Lösche Produkt, bzw verringere die Anzahl über - auf Template
    //Funktioniert!
    public int DeleteProductfromList(@PathVariable("productId") long productId, @PathVariable("id") long listId) {
        ShoppingList shoppingList = shoppingListRepo.findById(listId).get();
        List<ListItem> listItems = shoppingList.getList();
        for (ListItem listItem : listItems) {
            if (listItem.getProduct().getId() == productId) {
                if (listItem.getQuantity() > 0) {
                    listItem.setQuantity(listItem.getQuantity() - 1);
                    if (listItem.getQuantity() == 0) {
                        shoppingList.getList().remove(listItem);
                        shoppingListRepo.save(shoppingList);
                        listItemRepository.deleteById(listItem.getId());
                        return 0;
                    } else {
                        listItemRepository.save(listItem);
                        return listItem.getQuantity();
                    }
                }
            }
        }
        return 0;
    }

    @GetMapping("/{id}/deleteItem/{listItemId}")
    //Lösche Items (nicht Anzahl verändern)
    //Funktioniert!
    public void deleteItemfromList(@PathVariable("id") long listId, @PathVariable("listItemId") long listItemId) {
        listItemRepository.deleteById(listItemId);
    }

    @GetMapping("/{id}/products/{productId}/addSpeciFromProduct/{specificationId}")
    @ResponseBody
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addSpecificationfromProduct(@PathVariable("id") long id, @PathVariable("productId") long productId, @PathVariable("specificationId") long specificationId) {
        Product product = productRepo.findById(productId).get();
        ListItem listItem;
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                li.setSpecification(specificationRepository.findById(specificationId).get());
                listItemRepository.save(li);
                return li;
            }
        }
        listItem=new ListItem(1,productRepo.findById(productId).get(), shoppingListRepo.findById(id).get());
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
        return listItem;
    }

    @GetMapping("/{listId}/products/{productId}/addAmountFromProduct/{amountId}")
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public ListItem addAmountFromProduct(@PathVariable("listId") long listId, @PathVariable("productId") long productId, @PathVariable("amountId") long amountId) {
        Product product = productRepo.findById(productId).get();
        List<ListItem> listItems = shoppingListRepo.findById(listId).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                li.setAmount(amountRepository.findById(amountId).get());
                listItemRepository.save(li);
                return li;

            }
        }
        ListItem listItem=new ListItem(1,productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
        return listItem;
    }

    @GetMapping("/{id}/products/{productId}/isAmount/{amountId}")
    //Fügt Spezifikation hinzu von der ProduktListe aus
    public boolean isAmount(@PathVariable("id") long id, @PathVariable("productId") long productId, @PathVariable("amountId") long amountId) {
        Product product = productRepo.findById(productId).get();
        List<ListItem> listItems = shoppingListRepo.findById(id).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                if (li.getAmount().getId() == amountId) {
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/{id}/{itemId}/addSpecification/{specificationId}")
    //Fügt Spezifikation aus der Liste selbst hinzu
    public void addSpecification(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("specificationId") long specificationId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setSpecification(specificationRepository.findById(specificationId).get());
        listItemRepository.save(listItem);
    }

    @GetMapping("/{id}/{itemId}/{quantity}/buyItem")
    //Fügt Spezifikation aus der Liste selbst hinzu
    public void buyItem(@PathVariable("userId") long userId, @PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("quantity") int quantity) {
        Product product = listItemRepository.findById(itemId).get().getProduct();
        Statistic statistic=userRepository.findById(userId).get().getStatistics();
        List<StatisticItem> statisticItemList = statistic.getStatisticItemList();

        for (StatisticItem statisticItem : statisticItemList) {
            if (statisticItem.getProduct() == product) {
                statisticItem.setQuantity(statisticItem.getQuantity()+quantity);
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

    @GetMapping("/{id}/{itemId}/addAmount/{amountId}")
    //Fügt Menge aus der Liste selbst hinzu
    public void addAmount(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("amountId") long amountId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.setAmount(amountRepository.findById(amountId).get());
        listItemRepository.save(listItem);
    }

    @GetMapping("/{id}/{itemId}/removeAmount/{amountId}")
    //Fügt Menge aus der Liste selbst hinzu
    public void removeAmount(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("amountId") long amountId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeAmount();
        listItemRepository.save(listItem);
    }

    @GetMapping("/{id}/{itemId}/removeSpecification/{specificationId}")
    //entfernt Spezifikation aus der Liste aus, von der Liste
    public void removeSpecification(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("specificationId") long specificationId) {
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.removeSpecification();
        listItemRepository.save(listItem);
    }

    @GetMapping("/{id}/changePriority/{itemId}/{value}")
    //Ändert die Priorität, 3=rot --> Sehr wichtig, 2=gelb --> wichtig, 1=grün --> noch nicht allzu wichtig // Sinnvoll für WGS
    public List<ListItem> sortByPriority(@PathVariable("id") long id, @PathVariable("itemId") long itemId, @PathVariable("value") int value) {
        ShoppingList shoppingList = shoppingListRepo.findById(id).get();
        ListItem listItem = listItemRepository.findById(itemId).get();
        listItem.changePriority(value);
        listItemRepository.save(listItem);
        return shoppingList.getList();
    }

    @GetMapping("/{id}/checkStores/shop/{shopId}")
    //CheckStores Welche(und wie viele) Produkte haben die jeweiligen Shops von der Liste
    public List<Product> CheckShop(@PathVariable("id") long id, @PathVariable("shopId") long shopId) {
        List<ListItem> OnList = shoppingListRepo.findById(id).get().getList();
        List<Product> FoundProductsInShop = new ArrayList<Product>();
        List<Product> AllProductsInShop = shopRepository.findById(shopId).get().getProducts();
        for (ListItem listItem : OnList) {
            for (Product product : AllProductsInShop) {
                if (listItem.getProduct().getId() == product.getId()) {
                    FoundProductsInShop.add(product);
                    break;
                }
            }
        }
        return FoundProductsInShop;
    }

    //Nicht Realisiert
     /*@GetMapping("/{id}/products/{itemId}")     //Detailproduktanzeige
    @ResponseBody
    public Product detailProduct(Model model, @PathVariable("id") Long id, @PathVariable ("itemId") Long itemId)
    {
        model.addAttribute("product",listItemRepository.findById(itemId).get().getProduct());
        return listItemRepository.findById(itemId).get().getProduct();
    }*/
        /*@GetMapping("lists/{listId}/products/{productId}/addSpecification")
    public String newCostumSepecification(@PathVariable("listId") long listId, @PathVariable("productId") long productId, Model model, @ModelAttribute Specification specification) {
        String name=specification.getName();
        specificationRepository.save(new Specification(name));
        Product product=productRepo.findById(productId).get();
        product.addSpecification(specification);
        productRepo.save(product);
        return "redirect:/users/{userId}/lists/{listId}/products";
    }
    @GetMapping("lists/{listId}/products/{productId}/addCostumSpecification/{name}")
    public String addCostumSepecification(@PathVariable("listId") long listId, @PathVariable("productId") long productId, String name) {
        Specification specification= new Specification(name);
        specificationRepository.save(specification);
        Product product=productRepo.findById(productId).get();
        product.addSpecification(specification);
        productRepo.save(product);
        return name;
    }*/

}
