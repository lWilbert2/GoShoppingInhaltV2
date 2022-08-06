package com.goShopping.V2.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends CrudRepository <Amount, Long> {
    @Override
    List<Amount> findAll();
}
