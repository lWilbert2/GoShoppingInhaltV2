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
    @Autowired
    SpecificationRepository specificationRepository;
    @Autowired
    AmountRepository amountRepository;
    @Autowired
    StatisticRepository statisticRepository;


    @GetMapping("addEverything")
    public void AddEverything() {

        //Add User
        //Statistic statistic = new Statistic();
        //statisticRepository.save(statistic);
        //userRepository.save(new User("TestUser", statistic));


        // Vegane Vegetarische Erseatzproduckte
        productRepo.save(new Product("Fleischwurst (vegan)"));
        productRepo.save(new Product("Fischstäbchen (vegan)"));
        productRepo.save(new Product("Bratwurst (vegan)"));
        productRepo.save(new Product("Schnitzel (vegan)"));
        productRepo.save(new Product("Hackfleisch (vegan)"));
        productRepo.save(new Product("Milch (vegan)"));
        productRepo.save(new Product("Käse (vegan)"));
        productRepo.save(new Product("Tofu"));
        productRepo.save(new Product("Frikadellen (vegan)"));
        productRepo.save(new Product("Yogurt (vegan)"));

        //Fleisch & Fisch
        productRepo.save(new Product("Salami"));
        productRepo.save(new Product("Fleischwurst"));
        productRepo.save(new Product("Lachs"));
        productRepo.save(new Product("Fischstäbchen"));
        productRepo.save(new Product("Bockwürste"));
        productRepo.save(new Product("Bratwürste"));
        productRepo.save(new Product("Hähnchen "));
        productRepo.save(new Product("Schnitzel"));
        productRepo.save(new Product("Steak"));
        productRepo.save(new Product("Hackfleisch"));
        productRepo.save(new Product("Frikadellen"));

        //Tierische Produkte
        productRepo.save(new Product("Milch"));
        productRepo.save(new Product("Camenbert (Käse)"));
        productRepo.save(new Product("Feta (Käse)"));
        productRepo.save(new Product("Mozarella (Käse)"));
        productRepo.save(new Product("Gauda (Käse)"));
        productRepo.save(new Product("Gorgonzola (Käse)"));
        productRepo.save(new Product("Yogurt"));
        productRepo.save(new Product("Sahne"));
        productRepo.save(new Product("Creme Fresh"));
        productRepo.save(new Product("Rahm"));
        productRepo.save(new Product("Kondensmilch"));
        productRepo.save(new Product("Buttermilch"));
        productRepo.save(new Product("Eier"));


        //Gemüse  //Stück sinvoll und Gramm
        productRepo.save(new Product("Zwiebel"));
        productRepo.save(new Product("Paprika"));
        productRepo.save(new Product("Gurke"));
        productRepo.save(new Product("Tomate"));
        productRepo.save(new Product("Kohlrabi"));
        productRepo.save(new Product("Blumenkohl"));
        productRepo.save(new Product("Brokkoli "));
        productRepo.save(new Product("Aubergine"));
        productRepo.save(new Product("Zucchini "));
        productRepo.save(new Product("Kürbis"));
        productRepo.save(new Product("Porree"));
        productRepo.save(new Product("Kopfsalat"));
        productRepo.save(new Product("Eisbergsalat"));

        //Gramm Angaben
        productRepo.save(new Product("Möhren"));
        productRepo.save(new Product("Raddieschen"));
        productRepo.save(new Product("Brechbohnen"));
        productRepo.save(new Product("Champignons"));
        productRepo.save(new Product("Steinpilze"));
        productRepo.save(new Product("Knoblauch"));
        productRepo.save(new Product("Grünkohl"));
        productRepo.save(new Product("Erbsen"));
        productRepo.save(new Product("Spinat"));
        productRepo.save(new Product("Rucola"));
        productRepo.save(new Product("Kartoffeln"));

        //Obst  //Stück Sinnvoll
        productRepo.save(new Product("Apfel"));
        productRepo.save(new Product("Birne"));
        productRepo.save(new Product("Nektarine"));
        productRepo.save(new Product("Mango"));
        productRepo.save(new Product("Banane"));
        productRepo.save(new Product("Kiwi"));
        productRepo.save(new Product("Avocado"));
        productRepo.save(new Product("Melone"));
        productRepo.save(new Product("Orange"));

        productRepo.save(new Product("Himbeeren"));
        productRepo.save(new Product("Brombeeren"));
        productRepo.save(new Product("Erdbeeren"));
        productRepo.save(new Product("Heidelbeeren"));
        productRepo.save(new Product("Kirschen"));
        productRepo.save(new Product("Pflaumen"));
        productRepo.save(new Product("Trauben"));
        productRepo.save(new Product("Aprikosen"));


        //Getränke
        productRepo.save(new Product("Orangensaft"));
        productRepo.save(new Product("Apfelsaft"));
        productRepo.save(new Product("Wasser (sprudel)"));
        productRepo.save(new Product("Wasser (still)"));
        productRepo.save(new Product("Limonade"));
        productRepo.save(new Product("Cola"));
        productRepo.save(new Product("Smoothie"));
        productRepo.save(new Product("Eistee"));
        productRepo.save(new Product("Bier (alkoholfrei)"));

        //Alkohol ab 16
        productRepo.save(new Product("Bier"));
        productRepo.save(new Product("Rotwein"));
        productRepo.save(new Product("Weißwein"));
        productRepo.save(new Product("Prosecco"));
        productRepo.save(new Product("Sekt"));
        productRepo.save(new Product("Rosé"));

        //Alkohol ab 18
        productRepo.save(new Product("Wodka"));
        productRepo.save(new Product("Gin"));
        productRepo.save(new Product("Rum"));
        productRepo.save(new Product("Whisky"));
        productRepo.save(new Product("Obstbrand"));
        productRepo.save(new Product("Ouzo"));

        //Speiseschrank
        productRepo.save(new Product("Nudeln"));
        productRepo.save(new Product("Reis"));
        productRepo.save(new Product("Linsen"));
        productRepo.save(new Product("Brot"));
        productRepo.save(new Product("Toast"));
        productRepo.save(new Product("Öl"));
        productRepo.save(new Product("Essig"));
        productRepo.save(new Product("Salz"));
        productRepo.save(new Product("Pfeffer"));
        productRepo.save(new Product("Tomaten (getrocknet)"));
        productRepo.save(new Product("Oliven"));
        productRepo.save(new Product("Marmelade"));
        productRepo.save(new Product("Honig"));
        productRepo.save(new Product("Walnüsse"));
        productRepo.save(new Product("Haselnüsse"));
        //TK Speiseschrank
        productRepo.save(new Product("Pommes (tiefkühl)"));
        productRepo.save(new Product("Pizza (tiefkühl)"));
        productRepo.save(new Product("Kroketten (tiefkühl)"));

        //Snacks (herzhaft)
        productRepo.save(new Product("Chips"));
        productRepo.save(new Product("Flips"));
        productRepo.save(new Product("Salzstangen"));
        productRepo.save(new Product("Nachos"));
        productRepo.save(new Product("Erdnüsse"));

        //(süß)
        productRepo.save(new Product("Schokolade"));
        productRepo.save(new Product("Gummibärchen"));
        productRepo.save(new Product("Kekse"));
        productRepo.save(new Product("Eis"));
        productRepo.save(new Product("Popkorn"));
        productRepo.save(new Product("Marshmallows"));
        productRepo.save(new Product("Gebäck (süß)"));


        //Sonstiges
        productRepo.save(new Product("Tabak"));

        //Drogerie Artikel
        productRepo.save(new Product("Zahnbürste"));
        productRepo.save(new Product("Zahnpasta"));
        productRepo.save(new Product("Shampoo"));
        productRepo.save(new Product("Duschgel"));
        productRepo.save(new Product("Body Lotion"));
        productRepo.save(new Product("Rasierer"));
        productRepo.save(new Product("Creme"));

        //Haushaltsartikel
        productRepo.save(new Product("Backpapier"));
        productRepo.save(new Product("Mülltüten"));
        productRepo.save(new Product("Spühlmittel"));
        productRepo.save(new Product("Waschmittel"));
        productRepo.save(new Product("Klopapier"));
        productRepo.save(new Product("Küchenrolle"));
        productRepo.save(new Product("Schwämme"));
        productRepo.save(new Product("Lappen"));
        productRepo.save(new Product("Scheuermilch"));


        //Add Categories
        categoryRepository.save(new Category("Vegane/ Vegetarische Ersatzprodukte"));
        categoryRepository.save(new Category("Fleisch"));
        categoryRepository.save(new Category("Milchprodukte"));
        categoryRepository.save(new Category("Gemüse"));
        categoryRepository.save(new Category("Obst"));
        categoryRepository.save(new Category("Getränke"));
        categoryRepository.save(new Category("Speiseschrank"));
        categoryRepository.save(new Category("Snacks"));
        categoryRepository.save(new Category("Sonstiges"));

        //Add Shops
        shopRepository.save(new Shop("REWE", "Hohenzollernring 8"));
        shopRepository.save(new Shop("EDEKA", "Zülpicher Straße 49"));
        shopRepository.save(new Shop("ALDI", "Severinsstraße 76a"));
        shopRepository.save(new Shop("LIDL", "Barbarossaplatz 3"));

        //Add months
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

        //Add Specifications
        specificationRepository.save(new Specification("Hafer"));
        specificationRepository.save(new Specification("Soja"));
        specificationRepository.save(new Specification("Kokos"));
        specificationRepository.save(new Specification("Mandel"));

        specificationRepository.save(new Specification("Bio"));


        specificationRepository.save(new Specification("braun"));
        specificationRepository.save(new Specification("weiß"));

        specificationRepository.save(new Specification("rot"));
        specificationRepository.save(new Specification("gelb"));
        specificationRepository.save(new Specification("grün"));

        specificationRepository.save(new Specification("Konserve"));
        specificationRepository.save(new Specification("Glas"));

        specificationRepository.save(new Specification("frisch"));
        specificationRepository.save(new Specification("tiefgekühlt"));

        specificationRepository.save(new Specification("Fusilli"));
        specificationRepository.save(new Specification("Penne"));
        specificationRepository.save(new Specification("Spaghetti"));

        specificationRepository.save(new Specification("Erdbeer"));
        specificationRepository.save(new Specification("Mango"));
        specificationRepository.save(new Specification("Vanille"));
        specificationRepository.save(new Specification("Natur"));

        //Add Amounts
        amountRepository.save(new Amount("Stück"));
        amountRepository.save(new Amount("100g"));
        amountRepository.save(new Amount("200g"));
        amountRepository.save(new Amount("250g"));
        amountRepository.save(new Amount("500g"));
        amountRepository.save(new Amount("1kg"));
        amountRepository.save(new Amount("2kg"));
        amountRepository.save(new Amount("100ml"));
        amountRepository.save(new Amount("200ml"));
        amountRepository.save(new Amount("250ml"));
        amountRepository.save(new Amount("500ml"));
        amountRepository.save(new Amount("1l"));
        amountRepository.save(new Amount("2l"));

    }

    @GetMapping("addRelations")
    public void addRelations() {
        //Add Categories to products
        Category vegan = categoryRepository.findById(1L).get();
        for (long i = 1; i < 11; i++) {
            vegan.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(vegan);

        Category fleisch = categoryRepository.findById(2L).get();
        for (long i = 11; i < 22; i++) {
            fleisch.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(fleisch);

        Category milch = categoryRepository.findById(3L).get();
        for (long i = 22; i < 35; i++) {
            milch.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(milch);

        Category gemüse = categoryRepository.findById(4L).get();
        for (long i = 35; i < 59; i++) {
            gemüse.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(gemüse);

        Category obst = categoryRepository.findById(5L).get();
        for (long i = 59; i < 76; i++) {
            obst.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(obst);

        Category getränke = categoryRepository.findById(6L).get();
        for (long i = 76; i < 97; i++) {
            getränke.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(getränke);

        Category speiseschrank = categoryRepository.findById(7L).get();
        for (long i = 97; i < 115; i++) {
            speiseschrank.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(speiseschrank);

        Category snacks = categoryRepository.findById(8L).get();
        for (long i = 115; i < 127; i++) {
            snacks.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(snacks);

        Category sonstiges = categoryRepository.findById(9L).get();
        for (long i = 127; i < 143; i++) {
            sonstiges.addProduct(productRepo.findById(i).get());
        }
        categoryRepository.save(sonstiges);


        //Add Specifications
        //bio
        List<Product> Gemüse = categoryRepository.findById(4L).get().getProductsOfCategory();
        for (Product product : Gemüse) {
            product.addSpecification(specificationRepository.findById(5L).get());
            productRepo.save(product);
        }
        List<Product> Obst = categoryRepository.findById(5L).get().getProductsOfCategory();
        for (Product product : Obst) {
            product.addSpecification(specificationRepository.findById(5L).get());
            productRepo.save(product);
        }
        List<Product> Fleisch = categoryRepository.findById(2L).get().getProductsOfCategory();
        for (Product product : Fleisch) {
            product.addSpecification(specificationRepository.findById(5L).get());
            productRepo.save(product);
        }
        List<Product> Milch = categoryRepository.findById(3L).get().getProductsOfCategory();
        for (Product product : Milch) {
            product.addSpecification(specificationRepository.findById(5L).get());
            productRepo.save(product);
        }

        //rot/gelb/grün
        Product apfel = productRepo.findById(59L).get();
        Product birne = productRepo.findById(60L).get();
        Product paprika = productRepo.findById(36L).get();
        for (long i = 8; i < 11; i++) {
            apfel.addSpecification(specificationRepository.findById(i).get());
            birne.addSpecification(specificationRepository.findById(i).get());
            paprika.addSpecification(specificationRepository.findById(i).get());
        }
        productRepo.save(apfel);
        productRepo.save(birne);
        productRepo.save(paprika);

        //weiß/braun
        Product rum = productRepo.findById(93L).get();
        Product champions = productRepo.findById(51L).get();
        for (long i = 6; i < 8; i++) {
            rum.addSpecification(specificationRepository.findById(i).get());
            champions.addSpecification(specificationRepository.findById(i).get());
        }
        productRepo.save(rum);
        productRepo.save(champions);

        //mango/ erdbeer
        Product eis = productRepo.findById(123L).get();
        Product Smoothie = productRepo.findById(82L).get();
        Product yogurt = productRepo.findById(28L).get();
        Product buttermilch = productRepo.findById(33L).get();
        for (long i = 18; i < 20; i++) {
            Smoothie.addSpecification(specificationRepository.findById(i).get());
            eis.addSpecification(specificationRepository.findById(i).get());
            yogurt.addSpecification(specificationRepository.findById(i).get());
            buttermilch.addSpecification(specificationRepository.findById(i).get());
        }
        yogurt.addSpecification(specificationRepository.findById(21L).get());
        buttermilch.addSpecification(specificationRepository.findById(21L).get());

        productRepo.save(eis);
        productRepo.save(Smoothie);
        productRepo.save(yogurt);
        productRepo.save(buttermilch);

        //Frisch vs Tiefkühl
        Specification frisch = specificationRepository.findById(13L).get();
        Specification tk = specificationRepository.findById(14L).get();
        //Obst
        for (long i = 68; i < 72; i++) {
            Product product = productRepo.findById(i).get();
            product.addSpecification(frisch);
            product.addSpecification(tk);
            productRepo.save(product);
        }
        productRepo.findById(40L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(40L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(40L).get());
        productRepo.findById(41L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(41L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(41L).get());
        productRepo.findById(44L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(44L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(44L).get());
        productRepo.findById(54L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(54L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(54L).get());
        productRepo.findById(55L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(55L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(55L).get());
        productRepo.findById(56L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(56L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(56L).get());
        productRepo.findById(62L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(62L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(62L).get());
        productRepo.findById(13L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(13L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(13L).get());
        productRepo.findById(19L).get().addSpecification(specificationRepository.findById(13L).get());
        productRepo.findById(19L).get().addSpecification(specificationRepository.findById(14L).get());
        productRepo.save(productRepo.findById(19L).get());

        //hafer, mandel soja, kokos
        Product veganMilch = productRepo.findById(6L).get();
        Product veganYogurt = productRepo.findById(10L).get();
        for (long i = 1; i < 5; i++) {
            veganMilch.addSpecification(specificationRepository.findById(i).get());
            veganYogurt.addSpecification(specificationRepository.findById(i).get());
        }
        productRepo.save(veganMilch);
        productRepo.save(veganYogurt);


        //Fussili usw. Nudel Specificationen
        Product nudel = productRepo.findById(97L).get();
        for (long i = 15; i < 18; i++) {
            veganYogurt.addSpecification(specificationRepository.findById(i).get());
        }
        productRepo.save(nudel);

        //Add Amounts
        //Stück
        for (long i = 35; i < 48; i++) {
            Product product = productRepo.findById(i).get();
            product.addAmount(amountRepository.findById(1L).get());
            productRepo.save(product);

        }


        //100g
        //und ShopItems
        for (Product product : Gemüse) {
            product.addAmount(amountRepository.findById(2L).get());
            product.addAmount(amountRepository.findById(3L).get());
            product.addAmount(amountRepository.findById(4L).get());
            product.addAmount(amountRepository.findById(5L).get());
            product.addAmount(amountRepository.findById(6L).get());
            productRepo.save(product);
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, 24));
                    shopRepository.save(shop);
                }
            }
        }
        for (Product product : Obst) {
            product.addAmount(amountRepository.findById(2L).get());
            product.addAmount(amountRepository.findById(3L).get());
            product.addAmount(amountRepository.findById(4L).get());
            product.addAmount(amountRepository.findById(5L).get());
            product.addAmount(amountRepository.findById(6L).get());
            productRepo.save(product);
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() + 25 * 2)));
                    shopRepository.save(shop);
                }
            }
        }
        List<Product> Snacks = categoryRepository.findById(8L).get().getProductsOfCategory();
        for (Product product : Snacks) {
            product.addAmount(amountRepository.findById(2L).get());
            product.addAmount(amountRepository.findById(3L).get());
            productRepo.save(product);
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    if (product.getId() <= 119) { //Snacks Salzig
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 8)));
                    } else { //Snacks Süß
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 10)));
                    }
                    shopRepository.save(shop);
                }

            }
        }
        List<Product> Speiseschrank = categoryRepository.findById(7L).get().getProductsOfCategory();
        for (Product product : Speiseschrank) {
            product.addAmount(amountRepository.findById(2L).get());
            product.addAmount(amountRepository.findById(3L).get());
            product.addAmount(amountRepository.findById(6L).get());
            productRepo.save(product);
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 7 + 31)));
                    shopRepository.save(shop);

                }

            }
        }

        //1L, 500ml
        List<Product> productsOfGetränke = categoryRepository.findById(6L).get().getProductsOfCategory();
        for (Product product : productsOfGetränke) {
            product.addAmount(amountRepository.findById(11L).get());
            product.addAmount(amountRepository.findById(12L).get());
            productRepo.save(product);
            if (product.getId() < 85) {
                //Alkoholfreie Getränke
                for (long j = 1; j < 5; j++) {
                    if (Math.random() >= 0.4) {
                        Shop shop = shopRepository.findById(j).get();
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 3 + 4)));
                        shopRepository.save(shop);
                    }

                }
            } else if (product.getId() < 91) {
                //Alkohol ab 16
                for (long j = 1; j < 5; j++) {
                    if (Math.random() >= 0.4) {
                        Shop shop = shopRepository.findById(j).get();
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 16)));
                        shopRepository.save(shop);
                    }

                }
            } else {
                //Spirituosen
                for (long j = 1; j < 5; j++) {
                    if (Math.random() >= 0.4) {
                        Shop shop = shopRepository.findById(j).get();
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 18)));
                        shopRepository.save(shop);
                    }
                }
            }
        }
        List<Product> Sonstiges = categoryRepository.findById(9L).get().getProductsOfCategory();

        for (Product product : Sonstiges) {
            if (product.getId() == 127) //Tabak
            {
                for (long j = 1; j < 5; j++) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 43)));
                    shopRepository.save(shop);
                }

            } else if (product.getId() < 135) {
                //Kosmetik Artikel
                for (long j = 1; j < 5; j++) {
                    if (Math.random() >= 0.4) {
                        Shop shop = shopRepository.findById(j).get();
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 3 + 1)));
                        shopRepository.save(shop);
                    }

                }
            } else {
                //Haushaltsartikel
                for (long j = 1; j < 5; j++) {
                    if (Math.random() >= 0.4) {
                        Shop shop = shopRepository.findById(j).get();
                        shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 4 + 12)));
                        shopRepository.save(shop);
                    }

                }
            }
        }
        for (Product product : Fleisch) {
            product.addAmount(amountRepository.findById(2L).get());
            product.addAmount(amountRepository.findById(3L).get());
            product.addAmount(amountRepository.findById(4L).get());
            product.addAmount(amountRepository.findById(5L).get());
            product.addAmount(amountRepository.findById(6L).get());
            productRepo.save(product);
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, 29));
                    shopRepository.save(shop);
                }

            }
        }
        for (Product product : Milch) {
            //Milchprodukte Flüssig
            if (product.getId() == 22 || product.getId() > 27 && product.getId() < 34) {
                product.addAmount(amountRepository.findById(11L).get());
                product.addAmount(amountRepository.findById(12L).get());
            } else {
                //Milchprodukte in Gramm
                product.addAmount(amountRepository.findById(2L).get());
                product.addAmount(amountRepository.findById(3L).get());
                product.addAmount(amountRepository.findById(4L).get());
            }
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, (int) (Math.random() * 2 + 27)));
                    shopRepository.save(shop);
                }

            }
        }

        //Ersatzprodukte
        List<Product> Vegan = categoryRepository.findById(1L).get().getProductsOfCategory();
        for (Product product : Vegan) {
            if (product.getId() == 6) //vegane Milch in Liter
            {
                product.addAmount(amountRepository.findById(11L).get());
                product.addAmount(amountRepository.findById(12L).get());
            } else { //Rest in Gramm
                product.addAmount(amountRepository.findById(2L).get());
                product.addAmount(amountRepository.findById(3L).get());
                product.addAmount(amountRepository.findById(4L).get());
            }
            for (long j = 1; j < 5; j++) {
                if (Math.random() >= 0.4) {
                    Shop shop = shopRepository.findById(j).get();
                    shop.addShopItem(new ShopItem(shop, product, 30));
                    shopRepository.save(shop);
                }

            }
        }
//Saisonal August und September
        Month August = monthRepository.findById(8).get();
        August.addProduct(productRepo.findById(59L).get());
        August.addProduct(productRepo.findById(60L).get());
        August.addProduct(productRepo.findById(70L).get());
        August.addProduct(productRepo.findById(68L).get());
        August.addProduct(productRepo.findById(69L).get());
        August.addProduct(productRepo.findById(71L).get());
        August.addProduct(productRepo.findById(72L).get());
        August.addProduct(productRepo.findById(73L).get());
        August.addProduct(productRepo.findById(35L).get());
        August.addProduct(productRepo.findById(37L).get());
        August.addProduct(productRepo.findById(38L).get());
        August.addProduct(productRepo.findById(40L).get());
        August.addProduct(productRepo.findById(41L).get());
        August.addProduct(productRepo.findById(43L).get());
        August.addProduct(productRepo.findById(45L).get());
        August.addProduct(productRepo.findById(47L).get());
        August.addProduct(productRepo.findById(48L).get());
        August.addProduct(productRepo.findById(49L).get());
        August.addProduct(productRepo.findById(50L).get());
        August.addProduct(productRepo.findById(55L).get());
        August.addProduct(productRepo.findById(56L).get());
        August.addProduct(productRepo.findById(57L).get());
        August.addProduct(productRepo.findById(58L).get());


        monthRepository.save(August);

        Month September = monthRepository.findById(9).get();
        September.addProduct(productRepo.findById(59L).get());
        September.addProduct(productRepo.findById(60L).get());
        September.addProduct(productRepo.findById(68L).get());
        September.addProduct(productRepo.findById(69L).get());
        September.addProduct(productRepo.findById(71L).get());
        September.addProduct(productRepo.findById(73L).get());
        September.addProduct(productRepo.findById(31L).get());
        September.addProduct(productRepo.findById(40L).get());
        September.addProduct(productRepo.findById(41L).get());
        September.addProduct(productRepo.findById(50L).get());
        September.addProduct(productRepo.findById(55L).get());
        September.addProduct(productRepo.findById(54L).get());
        September.addProduct(productRepo.findById(58L).get());
        September.addProduct(productRepo.findById(44L).get());
        September.addProduct(productRepo.findById(48L).get());

        monthRepository.save(September);
        //Add Products to Shops


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
    public void AddProductToShop(@PathVariable("id") long id, @PathVariable("productId") long productId,
                                 @PathVariable("position") int position) {
        Shop shop = shopRepository.findById(id).get();
        ShopItem shopItem = new ShopItem(shop, productRepo.findById(productId).get(), position);
        shopItemRepository.save(shopItem);
        shop.addShopItem(shopItem);
        shopRepository.save(shop);
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
    public void addCategorytoProduct(@PathVariable("productId") long productId,
                                     @PathVariable("categoryId") long categoryId) {
        Product product = productRepo.findById(productId).get();
        Category category = categoryRepository.findById(categoryId).get();
        category.addProduct(product);
        categoryRepository.save(category);
    }

    @GetMapping("AddUsers")
    public void AddUsers() {
        //userRepository.save(new User("Melanie Becker"));
    }

    @GetMapping("DeleteUser")
    public void DeleteUser() {
        userRepository.deleteAll();
    }
}

