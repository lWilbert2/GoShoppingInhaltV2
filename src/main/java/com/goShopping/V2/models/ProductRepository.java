package com.goShopping.V2.models;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    ArrayList<Product> findAll();
    @Query("FROM Product ORDER BY name ASC")
    ArrayList<Product> findAllOrderByNameAsc();

}