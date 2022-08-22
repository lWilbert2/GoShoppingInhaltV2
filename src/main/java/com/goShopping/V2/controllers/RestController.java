package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import com.goShopping.V2.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static java.util.Calendar.MONTH;


//Controller für sonstige Operationen in anderen Datenbanken. Nicht für User gedacht
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MonthRepository monthRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShopItemRepository shopItemRepository;

    @GetMapping("users/{userId}/statistic/top5")
    public StatisticItem[] getTop5(Model model, @PathVariable("userId") long userId) {
        StatisticService t5s = new StatisticService();
        List<StatisticItem> statisticItems = userRepository.findById(userId).get().getStatistics().getStatisticItemList();
        return t5s.getTop5(statisticItems);
    }

    @GetMapping("users/{userId}/statistic/categories")
    public HashMap getCategories(Model model, @PathVariable("userId") long userId) {
        StatisticService statisticService = new StatisticService();
        return statisticService.categories(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
    }

    @GetMapping("shops")  //Gibt Liste aller shops zurück;
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    @GetMapping("shops/addShop")  //Startseite
    public void AddShops() {
        shopRepository.save(new Shop("REWE", "Hohenzollernring 8"));
        shopRepository.save(new Shop("EDEKA", "Zülpicher Straße 49"));
        shopRepository.save(new Shop("ALDI", "Severinsstraße 76a"));
        shopRepository.save(new Shop("LIDL", "Barbarossaplatz 3"));
    }

    @GetMapping("shops/{id}/addProduct/{productId}/{position}")  //Startseite
    @ResponseBody
    public void AddProductToShop(@PathVariable("id") long id, @PathVariable("productId") long productId, @PathVariable("position") int position) {
        Shop shop = shopRepository.findById(id).get();
        ShopItem shopItem = new ShopItem(shop, productRepo.findById(productId).get(), position);
        shopItemRepository.save(shopItem);
        shop.addShopItem(shopItem);
        shopRepository.save(shop);
    }

    @GetMapping("shops/addProducts")  //Startseite
    @ResponseBody
    public void AddProducts() {
        Shop shop = shopRepository.findById(1L).get();
        List<ShopItem> shopItems = new ArrayList<ShopItem>();

        //Adding Gemüse
        /*shopItems.add(new ShopItem(shop, productRepo.findById(7L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(8L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(9L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(11L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(12L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(16L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(17L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(18L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(24L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(33L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(34L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(39L).get(), 24));
        shopItems.add(new ShopItem(shop, productRepo.findById(48L).get(), 24));*/

        //Adding Obst
        shopItems.add(new ShopItem(shop, productRepo.findById(10L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(14L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(15L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(28L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(29L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(30L).get(), 25));
        shopItems.add(new ShopItem(shop, productRepo.findById(31L).get(), 26));
        shopItems.add(new ShopItem(shop, productRepo.findById(32L).get(), 26));
        shopItems.add(new ShopItem(shop, productRepo.findById(6L).get(), 26));

        for (ShopItem shopItem : shopItems) {
            shopItemRepository.save(shopItem);
            shop.addShopItem(shopItem);
        }
        shopRepository.save(shop);
    }

    @GetMapping("/seasonal")
    public List<Product> seasonalProducts() {
        Calendar now = Calendar.getInstance();
        Month currentMonth = monthRepository.findById(now.get(MONTH) + 1).get();
        return currentMonth.getProducts();

    }

    @GetMapping("/seasonal/addMonths")
    public List<Month> addMonths() {
        monthRepository.save(new Month("January"));
        monthRepository.save(new Month("February"));
        monthRepository.save(new Month("March"));
        monthRepository.save(new Month("April"));
        monthRepository.save(new Month("May"));
        monthRepository.save(new Month("June"));
        monthRepository.save(new Month("July"));
        monthRepository.save(new Month("August"));
        monthRepository.save(new Month("September"));
        monthRepository.save(new Month("October"));
        monthRepository.save(new Month("November"));
        monthRepository.save(new Month("December"));

        return monthRepository.findAll();
    }

    @GetMapping("/seasonal/addProduct")
    public Month addSeasonalProducts() {
        Month August = monthRepository.findById(8).get();
        August.addProduct(productRepo.findById(28L).get());
        August.addProduct(productRepo.findById(29L).get());
        August.addProduct(productRepo.findById(30L).get());
        August.addProduct(productRepo.findById(31L).get());
        August.addProduct(productRepo.findById(32L).get());
        August.addProduct(productRepo.findById(33L).get());
        August.addProduct(productRepo.findById(14L).get());
        August.addProduct(productRepo.findById(18L).get());
        August.addProduct(productRepo.findById(10L).get());
        monthRepository.save(August);
        return August;
    }

    public void findProduct() {

    }

    @GetMapping("AddUsers")
    public void AddUsers() {
        userRepository.save(new User("Melanie Becker"));
    }

    @GetMapping("DeleteUser")
    public void DeleteUser() {
        userRepository.deleteAll();
    }
}

