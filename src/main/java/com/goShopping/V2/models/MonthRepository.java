package com.goShopping.V2.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthRepository extends CrudRepository<Month, Integer> {
    @Override
    List<Month> findAll();

}
