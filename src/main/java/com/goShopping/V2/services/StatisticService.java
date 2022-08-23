package com.goShopping.V2.services;

import com.goShopping.V2.models.Category;
import com.goShopping.V2.models.Product;
import com.goShopping.V2.models.StatisticItem;

import java.util.HashMap;
import java.util.List;

public class StatisticService {
    public StatisticItem [] getTop5(List <StatisticItem> statisticItems )
    {
        StatisticItem unsorted[]=statisticItems.toArray(new StatisticItem[0]);
        quickSort(unsorted, 0, unsorted.length - 1);
        StatisticItem top5 []= new StatisticItem [5];
        for(int i=0; i<5; i++)
        {
            if(i<unsorted.length) {
                top5[i]=unsorted[i];
            }
            else {
                top5[i]=new StatisticItem(new Product("not defined"),0);
            }

        }
        return top5;

    }
    public HashMap<String, Integer> categories(List <StatisticItem> statisticItems)
    {
        HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
        for(StatisticItem statisticItem:statisticItems)
        {
            List <Category> categories=statisticItem.getProduct().getCategories();
            for(Category category:categories)
            {
                if(hashMap.containsKey(category.getName())) {
                    int value=hashMap.get(category.getName());
                    hashMap.put(category.getName(), value+statisticItem.getQuantity());
                }
                else {
                    hashMap.put(category.getName(),statisticItem.getQuantity());
                }
            }
        }
        return hashMap;
    }
    // method to find the partition position
    static int partition(StatisticItem array[], int low, int high) {

        // choose the rightmost element as pivot
        int pivot = array[high].getQuantity();

        // pointer for greater element
        int i = (low - 1);

        // traverse through all elements
        // compare each element with pivot
        for (int j = low; j < high; j++) {
            if (array[j].getQuantity() >= pivot) {

                // if element smaller than pivot is found
                // swap it with the greater element pointed by i
                i++;

                // swapping element at i with element at j
                StatisticItem temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // swapt the pivot element with the greater element specified by i
        StatisticItem temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // return the position from where partition is done
        return (i + 1);
    }
    static void quickSort(StatisticItem array[], int low, int high) {
        if (low < high) {

            // find pivot element such that
            // elements smaller than pivot are on the left
            // elements greater than pivot are on the right
            int pi = partition(array, low, high);

            // recursive call on the left of pivot
            quickSort(array, low, pi - 1);

            // recursive call on the right of pivot
            quickSort(array, pi + 1, high);
        }
    }
}
