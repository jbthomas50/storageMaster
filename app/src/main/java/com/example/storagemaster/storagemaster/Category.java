package com.example.storagemaster.storagemaster;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.widget.Toast;

/**
 * Created by Alex the one and only on 3/1/2018.
 */

public class Category {

    private String categoryName;
    public SortedList<Item> items;


    public void setCategoryName(String name){
        this.categoryName = name;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public boolean addItem(String name, int amount, int minimumAmount, Context context) {
        boolean itemFound = false;
        //Logic that ensures the item isn't already in the list
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getItemName() == name) {
                itemFound = true;
                Toast.makeText(context, "Item name already in list - Cannot Add", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (!itemFound) {
            Item item = new Item();
            item.setItemName(name);
            item.setQuantity(amount);
            item.setMin(minimumAmount);
            items.add(item);
            Toast.makeText(context, "Item successfully added to list", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void removeItem(String name, Context context){
        boolean itemFound = false;
        //Logic that searches for the item in the list
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getItemName() == name) {
                items.remove(items.get(i));
                itemFound = true;
                Toast.makeText(context, "Item successfully removed", Toast.LENGTH_SHORT).show();
            }
        }
        if (!itemFound)
            Toast.makeText(context, "Item not found", Toast.LENGTH_SHORT).show();
    }
}
