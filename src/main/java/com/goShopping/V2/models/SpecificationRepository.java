package com.goShopping.V2.models;

import com.goShopping.V2.models.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpecificationRepository extends CrudRepository<Specification, Long> {
    @Override
    List<Specification> findAll();
}