package com.example.storagemaster.storagemaster;

import java.util.Comparator;

/**
 * Created by JBThomas on 3/22/2018.
 */

public class ShoppingCompare implements Comparator<Item> {

    @Override
    public int compare(Item item, Item t1) {
        int first = 0;
        String codeName1 = item.getItemName().toString().toUpperCase();
        String codeName2 = t1.getItemName().toString().toUpperCase();

        if(item.getCrossed() && t1.getCrossed()){
            first = codeName1.compareTo(codeName2);
        }
        else if(item.getCrossed()){
            first = -1;
        }
        else if(t1.getCrossed()){
            first = 1;
        }
        else{
            first = codeName1.compareTo(codeName2);
        }

        return first;
    }
}
