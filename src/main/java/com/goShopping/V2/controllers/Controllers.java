package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import com.goShopping.V2.services.SearchService;
import com.goShopping.V2.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
        return "products";
    }

    @GetMapping("/lists/{listId}/categories/{id}/products")
    //Anzeige aller Produkte einer bestimmten Kategorie
    //TO DO: Alphabetisch Sortieren
    public String getProductsOfCategory(@PathVariable("userId") long userId, @PathVariable("listId") long listId, @PathVariable("id") long id, Model model) {
        SearchService searchService=new SearchService();
        User user = userRepository.findById(userId).get();
        Category category = categoryRepository.findById(id).get();
        List<ShoppingList> lists = user.getShoppingLists();
        List <Product> products=searchService.sortList(category.getProductsOfCategory());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingListRepo.findById(listId).get());
        model.addAttribute("category", category);
        model.addAttribute("product", products);
        model.addAttribute("specifications", specificationRepository.findAll());
        return "category";

    }

    @GetMapping("/lists/{listId}/categories/{categoryId}/products/{productId}/costum")
    //Fügt Costum hinzu von der ProduktListe aus
    public String CostumSpecificationCategoryList(@RequestParam ("costum")String costum, @PathVariable("categoryId") long categoryId, @PathVariable("listId") long listId, @PathVariable("productId") long productId) {
        Product product = productRepo.findById(productId).get();
        List<ListItem> listItems = shoppingListRepo.findById(listId).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                li.setCostum(costum);
                listItemRepository.save(li);
                return "redirect:/users/{userId}/lists/{listId}/categories/{categoryId}/products";
            }
        }
        ListItem listitem=new ListItem(1,product, shoppingListRepo.findById(listId).get());
        listitem.setCostum(costum);
        listItemRepository.save(listitem);
        return "redirect:/users/{userId}/lists/{listId}/categories/{categoryId}/products";
    }
    @GetMapping("/lists/{id}/checkStores")  //Aufruf der Seite CheckStores
    public String checkStoreSite(Model model, @PathVariable("id") long id, @PathVariable("userId") long userId) {
        model.addAttribute("shoppingList", shoppingListRepo.findById(id).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shop", shopRepository.findAll());
        return "checkStores";
    }
    @GetMapping("/lists/{listId}/shop/{shopId}")  //Aufruf der Seite CheckStores
    public String ShopLageplan(Model model, @PathVariable("listId") long listId, @PathVariable("userId") long userId) {
        model.addAttribute("shoppingList", shoppingListRepo.findById(listId).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shop", shopRepository.findAll());
        return "lageplan";
    }


    @GetMapping("lists/{id}/categories/10/products")
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
    @GetMapping("/lists/{listId}/search")
    public String Search(@RequestParam String search, Model model, @PathVariable("userId") long userId, @PathVariable("listId") long listId)
    {
        SearchService searchService=new SearchService();
       // Product product=searchService.binarySearchObject(productRepo.findAllOrderByNameAsc(), search);
        ArrayList <Product> products=searchService.subStringSubList(productRepo.findAllOrderByNameAsc(), search);
        model.addAttribute("product",products);
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingListRepo.findById(listId).get());
        return "products";
    }
    @GetMapping("lists/{listId}/{itemId}/addCostum")
    public String addCostum(@RequestParam("costum") String costum, @PathVariable("itemId") long itemId, @PathVariable("listId") long listId) {
        ListItem listitem=listItemRepository.findById(itemId).get();
        listitem.setCostum(costum);
        listItemRepository.save(listitem);
        return "redirect:/users/{userId}/lists/{listId}";
    }
    @GetMapping("/lists/{listId}/products/{productId}/costum")
    //Fügt Costum hinzu von der ProduktListe aus
    public String CostumSpecification(@RequestParam ("costum")String costum, @PathVariable("listId") long listId, @PathVariable("productId") long productId) {
        Product product = productRepo.findById(productId).get();
        List<ListItem> listItems = shoppingListRepo.findById(listId).get().getList();
        for (ListItem li : listItems) {
            if (li.getProduct().getId() == productId) {
                li.setCostum(costum);
                listItemRepository.save(li);
                return "redirect:/users/{userId}/lists/{listId}/products";
            }
        }
        ListItem listitem=new ListItem(1,product, shoppingListRepo.findById(listId).get());
        listitem.setCostum(costum);
        listItemRepository.save(listitem);
        return "redirect:/users/{userId}/lists/{listId}/products";
    }

    @GetMapping("/statistic")
    public String getStatistics(Model model, @PathVariable("userId") long userId)
    {
        StatisticService t5s=new StatisticService();
        StatisticItem [] statisticItems= t5s.getTop5(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
        //List <StatisticShop> statisticShops=userRepository.findById(userId).get().getStatistics().getStatisticShops();
        model.addAttribute("statisticItem",statisticItems);
        model.addAttribute("user",userRepository.findById(userId).get());
        //model.addAttribute("statisticShop",statisticShops);
        return "statistic";
    }
}
