package com.example.storagemaster.storagemaster;

import java.util.Comparator;

/**
 * Sorts shopping list with crossed-out items on bottom.
 */
public class ShoppingCompare implements Comparator<Item> {

    /**
     * compares items to sort alphabetically with crossed-out on bottom
     *
     * @param item first item to be compared
     * @param t1 second item to be compared
     * @return positive if item goes after, negative if it goes before.
     */
    @Override
    public int compare(Item item, Item t1) {
        int first = 0;
        String codeName1 = item.getItemName().toString().toUpperCase();
        String codeName2 = t1.getItemName().toString().toUpperCase();

        if(item.getCrossed() && t1.getCrossed()){
            first = codeName1.compareTo(codeName2);
        }
        else if(item.getCrossed()){
            first = 1;
        }
        else if(t1.getCrossed()){
            first = -1;
        }
        else{
            first = codeName1.compareTo(codeName2);
        }

        return first;
    }
}
