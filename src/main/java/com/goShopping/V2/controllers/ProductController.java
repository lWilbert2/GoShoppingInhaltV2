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
    public void addSpecification(@PathVariable("productId") long productId, @PathVariable("amountId") long amountId)
    {
        Product product=productRepo.findById(productId).get();
        product.addAmount(amountRepository.findById(amountId).get());
        productRepo.save(product);
    }
    @GetMapping("/addSpecification")
    @ResponseBody
    public void addSpecification()
    {
        Product product1=productRepo.findById(28L).get();
        product1.addSpecification(specificationRepository.findById(6L).get());
        product1.addSpecification(specificationRepository.findById(7L).get());
        product1.addSpecification(specificationRepository.findById(8L).get());
        productRepo.save(product1);
        Product product2=productRepo.findById(34L).get();
        product2.addSpecification(specificationRepository.findById(6L).get());
        product2.addSpecification(specificationRepository.findById(7L).get());
        product2.addSpecification(specificationRepository.findById(8L).get());
        productRepo.save(product2);
        Product product3=productRepo.findById(7L).get();
        product3.addSpecification(specificationRepository.findById(12L).get());
        productRepo.save(product3);
        Product product4=productRepo.findById(8L).get();
        product4.addSpecification(specificationRepository.findById(12L).get());
        productRepo.save(product4);
        Product product5=productRepo.findById(9L).get();
        product5.addSpecification(specificationRepository.findById(12L).get());
        productRepo.save(product5);
        Product product6=productRepo.findById(10L).get();
        product6.addSpecification(specificationRepository.findById(12L).get());
        productRepo.save(product6);
        Product product7=productRepo.findById(11L).get();
        product7.addSpecification(specificationRepository.findById(12L).get());
        productRepo.save(product7);

    }

     /*@GetMapping("/{id}/filter")
    @ResponseBody
    public List <Filter> getFilter(@PathVariable("id") long id)
    {
        Product product=productRepo.findById(id).get();
        return product.getFilter();

    }*/
   /* @GetMapping("/{id}/addFilter/{filterId}")
    @ResponseBody
    public Product addFilterToProduct(@PathVariable("id") long id,@PathVariable("filterId") long filterId)
    {
        Product product=productRepo.findById(id).get();
        Filter filter=allergenRepository.findById(filterId).get();
        product.addFilter(filter);
        return productRepo.save(product);
    }*/

}
