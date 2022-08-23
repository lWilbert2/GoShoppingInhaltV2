package com.goShopping.V2.controllers;

import com.goShopping.V2.models.StatisticItem;
import com.goShopping.V2.models.UserRepository;
import com.goShopping.V2.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;



//Statistic Operationen, genutzt vom Frontend
@RestController
public class StatisticController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("users/{userId}/statistic/top5")
    public StatisticItem[] getTop5(Model model, @PathVariable("userId") long userId) {
        StatisticService t5s = new StatisticService();
        List<StatisticItem> statisticItems = userRepository.findById(userId).get().getStatistics().getStatisticItemList();
        return t5s.getTop5(statisticItems);
    }

    @GetMapping("users/{userId}/statistic/categories")
    public HashMap getCategories(Model model, @PathVariable("userId") long userId) {
        StatisticService statisticService = new StatisticService();
        return statisticService.categories(userRepository.findById(userId).get().getStatistics().getStatisticItemList());
    }
}
