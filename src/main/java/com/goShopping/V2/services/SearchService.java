package com.goShopping.V2.services;

import com.goShopping.V2.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchService {


    //Bin�rsuche returnt Index des Objektes in der Liste
    public static int binarySearchIndex(ArrayList<Product> list, String search) {

        Product o = binarySearchObject(list, search);

        return list.indexOf(o);

    }

    //check ob das Objekt mit dem Namen "search" in der Liste enthalten ist
    public static boolean isPartOfList(ArrayList<Product> list, String search) {
        if(binarySearchObject(list, search) != null) {
            return true;
        }
        return false;
    }

    //Bin�rsuche
    /**
     * Ermittelt in jedem Schleifendurchgang das mittlere Element des aktuellen Intervalls,
     * ueberprueft ob dieses das gesuchte Element ist und wenn nicht, ob sich das gesuchte Element lexikographisch ober- oder unterhalb
     * des mittleren Elementes befindet.
     * Anschlie�end werden die Intervallgrenzen neu gesetzt.
     * Funktioniert nur mit einer sortierten Liste mit Indexzugriff O(1).
     * @param list
     * @param search
     * @return
     */
    public static Product binarySearchObject(ArrayList<Product> list, String search) {

        int first = 0;
        int last = list.size();
        String key = search.toLowerCase();

        while(first <= last) {

            int mid = first + (last - first)/2;
            String midName = list.get(mid).getName().toLowerCase();

            //Check ob gesuchtes Element in Intervallmitte steht
            if(key.compareTo(midName) == 0) {
                return list.get(mid);
            }
            //Check ob lexikographisch weiter hinten
            else if(key.compareTo(midName) > 0) {
                first = mid + 1;
            }
            //Kann nur noch lexikographisch kleiner sein (linke Haelfte des Intervalls)
            else {
                last = mid - 1;
            }
        }
        return null; // gesuchte Objekt ist nicht Teil der Liste
    }
    /**
     * Sortiert die uebergebene Liste alphabetisch nach den Namen der Objekte (nicht case sensitive)
     * @param list
     * @return sortierte Liste von Produkten
     */
    public static List<Product> sortList(List<Product> list) {


        Collections.sort(list, new Comparator<Product>() {

            public int compare(Product o1, Product o2) {

                return Integer.valueOf(	o1.getName().toLowerCase().compareTo(	o2.getName().toLowerCase()));

            }

        });
        return list;

    }

    /**
     * Sucht mittels Binarysearch nach einem Element, welches mit dem Teilstring searchKey beginnt.
     * Anschlie�end l�uft man so lange in beide Richtungen, bis die Elemente nichtmehr mit dem Teilstring beginnen.
     *
     * @param list
     * @param searchKey
     * @return 	Subliste bestehend aus allen Elementen der Liste, die mit dem Searchkey beginnen.
     */
    public static ArrayList<Product> sortedSubList(ArrayList<Product> list, String searchKey){
        ArrayList<Product> result = new ArrayList<>();
        String key = searchKey.toLowerCase();
        System.out.println(key);
        int first = 0;
        int last = list.size();
        while(first <= last) {
            int mid = first + (last - first)/2;
            String midName = list.get(mid).getName().toLowerCase();
            if(midName.startsWith(key)) {

                int subFloor = mid - 1;
                int subRoof = mid + 1;
                while(subFloor >= first) {

                    Product tmpObj = list.get(subFloor);
                    String tmp = tmpObj.getName().toLowerCase();

                    if(tmp.startsWith(key)) {
                        subFloor--;
                    }
                    else {
                        break;
                    }

                }
                while(subRoof <= last) {

                    Product tmpObj = list.get(subRoof);
                    String tmp = tmpObj.getName().toLowerCase();

                    if(tmp.startsWith(key)) {
                        subRoof++;
                    }
                    else {
                        break;
                    }

                }
                for(int i = subFloor + 1; i <= subRoof - 1; i++) {

                    result.add(list.get(i));

                }
                return result;
            }


            else if(key.compareTo(midName) > 0) {
                first = mid + 1;
            }

            else {
                last = mid - 1;
            }
        }
        return null;
    }

    /**
     * L�uft einmal �ber die Liste und checked bei jedem Listen-Element, ob dieses den searchkey im Namen enthaellt.
     * @param list
     * @param searchKey
     * @return	Subliste mit Elementen, welche die gesuchte Zeichenkette im Namen enthalten
     */
    public static ArrayList<Product> subStringSubList(ArrayList<Product> list, String searchKey){

        ArrayList<Product> result = new ArrayList<>();
        String key = searchKey.toLowerCase();
        for(Product o : list) {

            String name = o.getName().toLowerCase();

            if(name.contains(key)) {
                result.add(o);
            }

        }

        return result;
    }

    //Suchfunktion welcher Laden die meisten Items der Liste auf Lager hat
    public static ArrayList<Product> checkStoreInventory(ArrayList<Product> all, ArrayList<Product> storelist){
        ArrayList<Product> result = new ArrayList<>();

        for(Product o : storelist) {

            String oName = o.getName();
            //Hier die normale Binarysearch Methode aufrufen mit der all Liste und dem Namen bzw dem Objekt o, dass grad kontrolliert wird.
            if(isPartOfList(all, oName)) {
                result.add(o);
            }

        }
        return result;

    }

}
