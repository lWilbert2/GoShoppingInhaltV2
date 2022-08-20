package com.goShopping.V2.controllers;

import com.goShopping.V2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



//Controller für Operations, die Produkte bearbeiten, nicht für User Gedacht
@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AmountRepository amountRepository;
    //@Autowired
    //private FilterRepository allergenRepository;
    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping("/addProduct")  //Startseite
    public void products(Model model) {
        productRepo.save(new Product("Frucht Shake", "Japan"));
        /*productRepo.save(new Product("Erdbeeren", "Deutschland"));
        productRepo.save(new Product("Mango", "Puerto Rico"));
        productRepo.save(new Product("Chinakohl", "China"));
        productRepo.save(new Product("Champions", "Russland"));
        productRepo.save(new Product("Möhren", "Niederlande"));
        productRepo.save(new Product("Aperol Spritz", "Italien"));
        productRepo.save(new Product("Gummibärchen", "Thomas Gottschalk"));
        productRepo.save(new Product("Marmelade", "Frankreich"));
        productRepo.save(new Product("Brie", "Frankreich"));
        productRepo.save(new Product("Hobbits", "Auenland"));
        productRepo.save(new Product("Edamame", "Japan"));
        productRepo.save(new Product("Rotwein", "Griechenland"));
        productRepo.save(new Product("Weißwein", "Portugal"));*/
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") long id)
    {
        return productRepo.findById(id).get();
    }

    @GetMapping("/{id}/addCategory/{categoryId}")
    @ResponseBody
    public Product addCategorytoProduct(@PathVariable("id") long id,@PathVariable("categoryId") long categoryId)
    {
        Product product=productRepo.findById(id).get();
        Category category=categoryRepository.findById(categoryId).get();
        product.addCategory(category);
        return productRepo.save(product);
    }
    @GetMapping("/addProductsToCategory")
    @ResponseBody
    public void addCategoriestoProduct()
    {
        Category category=categoryRepository.findById(4l).get();
        category.addProduct(productRepo.findById(7L).get());
        category.addProduct(productRepo.findById(8L).get());
        category.addProduct(productRepo.findById(9L).get());
        category.addProduct(productRepo.findById(11L).get());
        category.addProduct(productRepo.findById(12L).get());
        category.addProduct(productRepo.findById(16L).get());
        category.addProduct(productRepo.findById(17L).get());
        category.addProduct(productRepo.findById(18L).get());
        category.addProduct(productRepo.findById(24L).get());
        category.addProduct(productRepo.findById(33L).get());
        category.addProduct(productRepo.findById(34L).get());
        category.addProduct(productRepo.findById(39L).get());
        category.addProduct(productRepo.findById(48L).get());
        categoryRepository.save(category);

        Category category2=categoryRepository.findById(5l).get();
        category2.addProduct(productRepo.findById(10L).get());
        category2.addProduct(productRepo.findById(14L).get());
        category2.addProduct(productRepo.findById(15L).get());
        category2.addProduct(productRepo.findById(28L).get());
        category2.addProduct(productRepo.findById(29L).get());
        category2.addProduct(productRepo.findById(30L).get());
        category2.addProduct(productRepo.findById(31L).get());
        category2.addProduct(productRepo.findById(32L).get());
        category2.addProduct(productRepo.findById(6L).get());

        categoryRepository.save(category2);

        Category category3=categoryRepository.findById(6l).get();
        category3.addProduct(productRepo.findById(3L).get());
        category3.addProduct(productRepo.findById(4L).get());
        category3.addProduct(productRepo.findById(13L).get());
        category3.addProduct(productRepo.findById(19L).get());
        category3.addProduct(productRepo.findById(25L).get());
        category3.addProduct(productRepo.findById(26L).get());
        category3.addProduct(productRepo.findById(27L).get());
        category3.addProduct(productRepo.findById(35L).get());
        category3.addProduct(productRepo.findById(36L).get());
        category3.addProduct(productRepo.findById(37L).get());
        categoryRepository.save(category3);

        Category category4=categoryRepository.findById(7l).get();
        category4.addProduct(productRepo.findById(1L).get());
        category4.addProduct(productRepo.findById(21L).get());
        category4.addProduct(productRepo.findById(23L).get());
        category4.addProduct(productRepo.findById(47L).get());
        category4.addProduct(productRepo.findById(49L).get());
        category4.addProduct(productRepo.findById(55L).get());
        category4.addProduct(productRepo.findById(50L).get());
        categoryRepository.save(category4);

        Category category5=categoryRepository.findById(8l).get();
        category5.addProduct(productRepo.findById(1L).get());
        category5.addProduct(productRepo.findById(5L).get());
        category5.addProduct(productRepo.findById(20L).get());
        category5.addProduct(productRepo.findById(46L).get());
        categoryRepository.save(category5);

        Category category6=categoryRepository.findById(9l).get();
        category6.addProduct(productRepo.findById(56L).get());
        category6.addProduct(productRepo.findById(57L).get());
        category6.addProduct(productRepo.findById(58L).get());
        categoryRepository.save(category6);

        Category category7=categoryRepository.findById(1l).get();
        category7.addProduct(productRepo.findById(13L).get());
        category7.addProduct(productRepo.findById(42L).get());
        category7.addProduct(productRepo.findById(45L).get());
        category7.addProduct(productRepo.findById(55L).get());
        categoryRepository.save(category7);

        Category category8=categoryRepository.findById(2l).get();
        category8.addProduct(productRepo.findById(43L).get());
        category8.addProduct(productRepo.findById(40L).get());
        category8.addProduct(productRepo.findById(54L).get());
        categoryRepository.save(category8);

        Category category9=categoryRepository.findById(3l).get();
        category9.addProduct(productRepo.findById(22L).get());
        category9.addProduct(productRepo.findById(51L).get());
        category9.addProduct(productRepo.findById(52L).get());
        category9.addProduct(productRepo.findById(53L).get());
        category9.addProduct(productRepo.findById(44L).get());
        category9.addProduct(productRepo.findById(37L).get());
        category9.addProduct(productRepo.findById(59L).get());
        categoryRepository.save(category9);



    }
    @GetMapping("/{id}/deleteCategory/{categoryId}")
    @ResponseBody
    public Product deleteCategoryfromProduct(@PathVariable("id") long id,@PathVariable("categoryId") long categoryId)
    {
        Product product=productRepo.findById(id).get();
        product.deleteCategory(categoryRepository.findById(categoryId).get());
        return productRepo.save(product);
    }
    @GetMapping("/{id}/categories")
    @ResponseBody
    public List<Category> getCategories(@PathVariable("id") long id)
    {
        Product product=productRepo.findById(id).get();
        return product.getCategories();

    }
    @GetMapping("/{productId}/addAmount/{amountId}")
    @ResponseBody
    public void addAmount(@PathVariable("productId") long productId, @PathVariable("amountId") long amountId)
    {
        Product product=productRepo.findById(productId).get();
        product.addAmount(amountRepository.findById(amountId).get());
        productRepo.save(product);
    }
    @GetMapping("/{productId}/addSpecification/{specificationId}")
    public void addSpecification(@PathVariable("productId") long productId, @PathVariable("specificationId") long specificationId)
    {
        Product product=productRepo.findById(productId).get();
        product.addSpecification(specificationRepository.findById(specificationId).get());
        productRepo.save(product);
    }

}
