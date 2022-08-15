package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import com.goShopping.V2.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
   // @Autowired
   // FilterRepository filterRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("users/{userId}/statistic/top5")
    public StatisticItem [] getTop5(Model model, @PathVariable("userId") long userId)
    {
        StatisticService t5s=new StatisticService();
        StatisticItem [] statisticItems= t5s.getTop5(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
        return statisticItems;
    }
    @GetMapping("users/{userId}/statistic/categories")
    public HashMap getCategories(Model model, @PathVariable("userId") long userId)
    {
        StatisticService statisticService=new StatisticService();
        return statisticService.categories(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
    }
    @GetMapping("shops")  //Gibt Liste aller shops zurück;
    public List <Shop> getShops() {
        return shopRepository.findAll();
    }

    @GetMapping("shops/addShop")  //Startseite
    public void AddShops() {
        shopRepository.save(new Shop("REWE", "Hohenzollernring 8"));
        shopRepository.save(new Shop("EDEKA", "Zülpicher Straße 49"));
        shopRepository.save(new Shop("ALDI", "Severinsstraße 76a"));
        shopRepository.save(new Shop("LIDL", "Barbarossaplatz 3"));
    }
    @GetMapping("shops/{id}/addProduct/{productId}")  //Startseite
    @ResponseBody
    public void AddProductToShop(@PathVariable("id") long id, @PathVariable("productId") long productId) {
        Shop shop=shopRepository.findById(id).get();
        for(Product product: shop.getProducts())
        {
            if(product.getId()==productId)
            {
                return;
            }
        }
        shop.addProduct(productRepo.findById(productId).get());
        shopRepository.save(shop);
    }

    @GetMapping("/seasonal")
    public List <Product> seasonalProducts()
    {
       Calendar now=Calendar.getInstance();
       Month currentMonth= monthRepository.findById(now.get(MONTH)+1).get();
       return currentMonth.getProducts();

    }
    @GetMapping("/seasonal/addMonths")
    public List <Month> addMonths()
    {
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
    public Month addSeasonalProducts()
    {
        Month August=monthRepository.findById(8).get();
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
    public void findProduct()
    {

    }

   /* @GetMapping("/filter/addFilter/{name}")
    public void addAllergen(@PathVariable("name") String name)
    {
        Filter filter= new Filter(name);
        filterRepository.save(filter);
    }*/
    /*@GetMapping("/filter/{id}")
    public List<Product> getProductsWithFilter(@PathVariable("id") long id)
    {
        return filterRepository.findById(id).get().getProductsWithFilter();
    }*/

    @GetMapping("AddUsers")
    public void AddUsers()
    {
        userRepository.save(new User("Anna Neumann"));
        userRepository.save(new User("Samuel Neumann"));
        userRepository.save(new User("Klaus Kleber"));
        userRepository.save(new User("Eva Berber"));
    }
    /*@GetMapping("{userId}/settings/isFilter")
    public void isFilter(@PathVariable("userId") long userId)
    {
        User user=userRepository.findById(userId).get();
        Settings settings=user.getSettings();
        settings.setIsFilter(!settings.getIsFilter());
        user.setSettings(settings);
        userRepository.save(user);
    }*/
   /* @GetMapping("{userId}/settings/setFilter/{filterId}")
    public void filterFor(@PathVariable("userId") long userId, @PathVariable("filterId") long filterId)
    {
        User user=userRepository.findById(userId).get();
        Settings settings=user.getSettings();
        settings.addFilter(filterRepository.findById(filterId).get());
        user.setSettings(settings);
        userRepository.save(user);
    }*/

    @GetMapping("DeleteUser")
    public void DeleteUser()
    {
        userRepository.deleteAll();
    }
}

