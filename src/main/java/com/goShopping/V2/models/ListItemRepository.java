package com.goShopping.V2.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository extends CrudRepository<ListItem,Long> {
    @Override
    List<ListItem> findAll();
    @Query("FROM ListItem ORDER BY priority DESC")
    List<ListItem> findAllOrderByPriorityDesc();
}
