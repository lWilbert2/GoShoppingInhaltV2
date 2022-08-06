package com.goShopping.V2.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository <Shop,Long> {
    @Override
    List<Shop> findAll();
}
