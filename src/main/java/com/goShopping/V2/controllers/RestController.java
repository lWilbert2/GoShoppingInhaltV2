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
        shopItems.add(new ShopItem(shop, productRepo.findById(7L).get(), 24));
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
        shopItems.add(new ShopItem(shop, productRepo.findById(48L).get(), 24));

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

        //Getränke hinzufuegen (Manuell, da Unterscheidung zwischen Spirituose und normalen Getränken)
        shopItems.add(new ShopItem(shop, productRepo.findById(61L).get(), 4));
        shopItems.add(new ShopItem(shop, productRepo.findById(3L).get(), 16));
        shopItems.add(new ShopItem(shop, productRepo.findById(4L).get(), 17));
        shopItems.add(new ShopItem(shop, productRepo.findById(19L).get(), 18));
        shopItems.add(new ShopItem(shop, productRepo.findById(27L).get(), 5));
        shopItems.add(new ShopItem(shop, productRepo.findById(35L).get(), 5));
        shopItems.add(new ShopItem(shop, productRepo.findById(36L).get(), 6));
        shopItems.add(new ShopItem(shop, productRepo.findById(26L).get(), 19));
        shopItems.add(new ShopItem(shop, productRepo.findById(61L).get(), 4));
        shopItems.add(new ShopItem(shop, productRepo.findById(25L).get(), 19));
        shopRepository.save(shop);
        for (ShopItem shopItem : shopItems) {
            shopItemRepository.save(shopItem);
            shop.addShopItem(shopItem);
        }
        List <Product> products=categoryRepository.findById(1L).get().getProductsOfCategory();
        for(Product product: products)
        {
            ShopItem shopItem=new ShopItem(shop,product,30);
            shopItemRepository.save(shopItem);
            shop.addShopItem(shopItem);
        }
        List <Product> products2=categoryRepository.findById(2L).get().getProductsOfCategory();
        for(Product product: products2)
        {
            ShopItem shopItem=new ShopItem(shop,product,29);
            shopItemRepository.save(shopItem);
            shop.addShopItem(shopItem);
        }
        List <Product> products3=categoryRepository.findById(3L).get().getProductsOfCategory();
        for(Product product: products3)
        {
            ShopItem shopItem=new ShopItem(shop,product,(int)(Math.random()*2+27));
            shopItemRepository.save(shopItem);
            shop.addShopItem(shopItem);
        }

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

    @GetMapping("category/{categoryId}/addProduct/{productId}")
    @ResponseBody
    public void addCategorytoProduct(@PathVariable("productId") long productId,@PathVariable("categoryId") long categoryId)
    {
        Product product=productRepo.findById(productId).get();
        Category category=categoryRepository.findById(categoryId).get();
       category.addProduct(product);
       categoryRepository.save(category);
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

