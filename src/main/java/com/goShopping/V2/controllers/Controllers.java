package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import com.goShopping.V2.services.SearchAndSortService;
import com.goShopping.V2.services.StatisticService;
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

    @GetMapping("/lists/{listId}")
    //Anzeigen der ListItems der jeweiligen Liste, Gibt eine nach Priorität sortierte Liste zurück
    public String list(@PathVariable("userId") long userId, Model model, @PathVariable("listId") Long listId) {
        ShoppingList shoppingList = shoppingListRepo.findById(listId).get();
        SearchAndSortService saSS = new SearchAndSortService();
        ListItem sorted[] = saSS.sortbyPrio(shoppingList.getList());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppingList", shoppingList);
        model.addAttribute("listItems", sorted);
        return "productsOnList";
    }

    @GetMapping("/lists/{listId}/products")
    //Anzeige aller Produkte, Alphabetisch sortiert, können per +,- der Liste hinzugefuegt werden
    public String productsToList(@PathVariable("userId") long userId, @PathVariable("listId") Long listId, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppingList", shoppingListRepo.findById(listId).get());
        model.addAttribute("product", productRepo.findAllOrderByNameAsc());
        return "products";
    }

    @GetMapping("/lists/{listId}/categories/{categoryId}/products")
    //Anzeige aller Produkte einer bestimmten Kategorie sortiert nach Alphabet
    public String getProductsOfCategory(@PathVariable("userId") long userId, @PathVariable("listId") long listId, @PathVariable("categoryId") long categoryId, Model model) {
        SearchAndSortService searchAndSortService = new SearchAndSortService();
        Category category = categoryRepository.findById(categoryId).get();
        List<Product> products = searchAndSortService.sortList(category.getProductsOfCategory());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppingList", shoppingListRepo.findById(listId).get());
        model.addAttribute("category", category);
        model.addAttribute("product", products);
        model.addAttribute("specifications", specificationRepository.findAll());
        return "category";

    }

    @GetMapping("/lists/{listId}/categories/{categoryId}/products/{productId}/custom")
    //Fügt custom hinzu von der ProduktListe aus
    public String CustomSpecificationCategoryList(@RequestParam("custom") String custom, @PathVariable("categoryId") long categoryId, @PathVariable("listId") long listId, @PathVariable("productId") long productId) {
        ListItem listItem = listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
        if (listItem != null) {
            listItem.setCustom(custom);
            listItemRepository.save(listItem);
            return "redirect:/users/{userId}/lists/{listId}/categories/{categoryId}/products";
        }
        listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setCustom(custom);
        listItemRepository.save(listItem);
        return "redirect:/users/{userId}/lists/{listId}/categories/{categoryId}/products";
    }

    @GetMapping("/lists/{listId}/shops/check")  //Aufruf der Seite CheckStores
    public String checkStoreSite(Model model, @PathVariable("listId") long listId, @PathVariable("userId") long userId) {
        model.addAttribute("shoppingList", shoppingListRepo.findById(listId).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shop", shopRepository.findAll());
        return "checkStores";
    }

    @GetMapping("/lists/{listId}/shops/{shopId}") //Aufrufen der Lageplanseite, die Position aller Produkte, die sowohl im Laden, als auch auf der Liste sind wird auf Klick angezeigt
    public String ShopLageplan(Model model, @PathVariable("listId") long listId, @PathVariable("userId") long userId, @PathVariable("shopId") long shopId) {
        model.addAttribute("shoppingList", shoppingListRepo.findById(listId).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shop", shopRepository.findById(shopId).get());
        return "lageplan";
    }

    @GetMapping("lists/{id}/categories/10/products")
    // Seasonal Service, übergibt eine alphabetisch sortierte Liste aus Produkte, welche in einem bestimmten Monat (dem Monat, der gerade ist) saison haben
    public String seasonalProducts(@PathVariable("userId") long userId, Model model, @PathVariable("id") long id) {
        SearchAndSortService searchAndSortService = new SearchAndSortService();
        Calendar now = Calendar.getInstance();
        Month currentMonth = monthRepository.findById(now.get(MONTH) + 1).get();
        List <Product> products=currentMonth.getProducts();
        model.addAttribute("category", currentMonth);
        model.addAttribute("shoppingList", shoppingListRepo.findById(id).get());
        model.addAttribute("product", SearchAndSortService.sortList(products));
        model.addAttribute("user", userRepository.findById(userId).get());
        return "category";

    }
    @GetMapping("/lists/{listId}/search")
    public String Search(@RequestParam String search, Model model, @PathVariable("userId") long userId, @PathVariable("listId") long listId) {
        SearchAndSortService searchAndSortService = new SearchAndSortService();
        // Product product=searchService.binarySearchObject(productRepo.findAllOrderByNameAsc(), search);
        ArrayList<Product> products = searchAndSortService.subStringSubList(productRepo.findAllOrderByNameAsc(), search);
        model.addAttribute("product", products);
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("shoppinglist", shoppingListRepo.findById(listId).get());
        return "products";
    }

    @GetMapping("lists/{listId}/items/{itemId}/custom")
    public String addCustom(@RequestParam("custom") String custom, @PathVariable("itemId") long itemId, @PathVariable("listId") long listId) {
        ListItem listitem = listItemRepository.findById(itemId).get();
        listitem.setCustom(custom);
        listItemRepository.save(listitem);
        return "redirect:/users/{userId}/lists/{listId}";
    }

    @GetMapping("/lists/{listId}/products/{productId}/custom")
    //Fügt custom hinzu von der ProduktListe aus
    public String CustomSpecification(@RequestParam("custom") String custom, @PathVariable("listId") long listId, @PathVariable("productId") long productId) {

        ListItem listItem = listItemRepository.findByShoppingList_IdAndProduct_Id(listId, productId);
        if (listItem != null) {
            listItem.setCustom(custom);
            listItemRepository.save(listItem);
            return "redirect:/users/{userId}/lists/{listId}/products";
        }
        listItem = new ListItem(1, productRepo.findById(productId).get(), shoppingListRepo.findById(listId).get());
        listItem.setCustom(custom);
        listItemRepository.save(listItem);
        return"redirect:/users/{userId}/lists/{listId}/products";
}

    @GetMapping("/statistic")
    public String getStatistics(Model model, @PathVariable("userId") long userId) {
        StatisticService t5s = new StatisticService();
        StatisticItem[] statisticItems = t5s.getTop5(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
        model.addAttribute("statisticItem", statisticItems);
        model.addAttribute("user", userRepository.findById(userId).get());
        return "statistic";
    }
}
