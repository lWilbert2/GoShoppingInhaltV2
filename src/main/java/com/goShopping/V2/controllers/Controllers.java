package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.MONTH;

//Kontroller für alle Frontendseiten
@Controller
@RequestMapping("users/{userId}")
public class Controllers {
    @Autowired
    private ShoppingListRepository shoppingListRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping("/")
    public String newDesigntest(Model model) {
    return "NewDesign";
    }

    @GetMapping("/lists")
    //Zeigt die Listen des Users nach dem Login
    public String getList(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("lists", userRepository.findById(userId).get().getShoppingLists());
        return "lists";
    }

    @GetMapping("/lists/newList")
    //Seite, um eine Neue Liste zu erstellen
    public String newlist(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppingList", new ShoppingList());
        return ("newList");
    }

    @PostMapping("lists/newList")
    //erstellt die neue Liste und gibt eine Message zurück
    public String newList(@PathVariable("userId") long userId, Model model, @ModelAttribute ShoppingList shoppingList, RedirectAttributes redirectAttributes) {
        String name = shoppingList.getName();
        shoppingList.setUser(userRepository.findById(userId).get());
        shoppingListRepo.save(shoppingList);
        redirectAttributes.addFlashAttribute("message", name);
        return "redirect:/users/{userId}/lists/newList";
    }

    @GetMapping("/lists/{id}")
    //Anzeigen der ListItems der jeweiligen Liste, Gibt eine nach Priorität sortierte Liste zurück
    public String list(@PathVariable("userId") long userId, Model model, @PathVariable("id") Long id) {
        List<ListItem> listItems = listItemRepository.findAllOrderByPriorityDesc();
        List<ListItem> sortedListItems = new ArrayList<ListItem>();
        for (ListItem l : listItems) {
            if (l.getShoppingList().getId() == id) {
                sortedListItems.add(l);
            }
        }
        ShoppingList shoppingList = shoppingListRepo.findById(id).get();
        shoppingList.setList(sortedListItems);
        shoppingListRepo.save(shoppingList);
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingList);
        return "productsOnList";
    }

    @GetMapping("/lists/{id}/products")
    //Anzeige aller Produkte, Alphabetisch sortiert, können per +,- der Liste hinzugefuegt werden
    //TO DO: Problem mit Spezifikationen, überlegen, wie genau das Laufen soll. Eigene Tags hinzufuegen funktioniert noch nicht.
    public String productsToList(@PathVariable("userId") long userId, @PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingListRepo.findById(id).get());
        model.addAttribute("product", productRepo.findAllOrderByNameAsc());
        model.addAttribute("listitem", shoppingListRepo.findById(id).get().getList());
        return "products";
    }

    @GetMapping("/lists/{listId}/categories/{id}/products")
    //Anzeige aller Produkte einer bestimmten Kategorie, Noch nicht alphabetisch sortiert
    //TO DO: Alphabetisch Sortieren
    public String getProductsOfCategory(@PathVariable("userId") long userId, @PathVariable("listId") long listId, @PathVariable("id") long id, Model model) {
        User user = userRepository.findById(userId).get();
        Category category = categoryRepository.findById(id).get();
        List<ShoppingList> lists = user.getShoppingLists();
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingListRepo.findById(listId).get());
        model.addAttribute("category", category);
        model.addAttribute("product", category.getProductsOfCategory());
        model.addAttribute("specifications", specificationRepository.findAll());
        return "category";

    }

    @GetMapping("/lists/{id}/checkStores")  //Aufruf der Seite CheckStores
    public String checkStoreSite(Model model, @PathVariable("id") long id, @PathVariable("userId") long userId) {
        model.addAttribute("shoppingList", shoppingListRepo.findById(id).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shop", shopRepository.findAll());
        return "checkStores";
    }


    @GetMapping("lists/{id}/categories/seasonal/products")
    // Seasonal Service, gibt Produkte zurück, welche in einem bestimmten Monat saison haben
    //TO DO: noch nicht Alphabetisch sortiert
    public String seasonalProducts(@PathVariable("userId") long userId, Model model, @PathVariable("id") long id) {
        Calendar now = Calendar.getInstance();
        Month currentMonth = monthRepository.findById(now.get(MONTH) + 1).get();
        model.addAttribute("category", currentMonth);
        model.addAttribute("shoppinglist", shoppingListRepo.findById(id).get());
        model.addAttribute("product", currentMonth.getProducts());
        model.addAttribute("user", userRepository.findById(userId).get());
        return "category";

    }

    @GetMapping("statistic")
    public String getStatistics(Model model, @PathVariable("userId") long userId)
    {
        List <StatisticItem> statisticItems= userRepository.findById(userId).get().getStatistics().getStatisticItemList();
        List <StatisticShop> statisticShops=userRepository.findById(userId).get().getStatistics().getStatisticShops();
        model.addAttribute("statisticItem",statisticItems);
        model.addAttribute("statisticShop",statisticShops);
        return "statistic";
    }
/*
    @PostMapping("/lists/{id}/{ProductID}/add")   //Produkt hinzufuegen. Vlt einzelne Produkte in Auswahlliste, Controller erzeugt ProduktBundle mit Stückanzahl in Einkaufsliste?
    public String addProduct(@RequestParam("currentList")shoppinglist s, @RequestParam("product")product p, @RequestParam("number") int num)
    {
        s.changeNumById(p.getId(),num);
        return "redirect: /";
    }
    @PostMapping("/lists/{id}/{ProductID}/delete")
    public String deleteProduct(@RequestParam("current List")shoppinglist s, @RequestParam("product")product p)
    {
        s.deleteProduct(p);
        return "deleted";
    }

     @PostMapping("/lists/{id}/{ProductID}/changenum")
    public String changeNum(@RequestParam("current List")shoppinglist s, @RequestParam("product")product p, @RequestParam("number") int num)   //Anzahl des Produkts in Liste ändern
    {
      p.setCount(num);
        if(num==0)
        {
            s.deleteProduct(p);
        }
        return "NumChanged";
    }

     */

}
