package com.example.storagemaster.storagemaster;



import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ME on 3/2/2018.
 */

public class User {
private String name;
private String password;
public ArrayList<Category> inventory;

public ArrayList<Category> getShoppingList()
{
    ArrayList<Category> list = new ArrayList<Category>(); //Main list that we'll return
    //Searching through the categories
    for (int i = 0; i < inventory.size(); i++)
    {
        ArrayList<Item> tempItems = inventory.get(i).items; //Items we'll look through
        boolean itemShortageFound = false;
        Category shoppingCategory = new Category(); //New category to add to shopping list
        //Searching through the item list
        for (int j = 0; j < tempItems.size(); j++) {
            //If the quantity is less than the desired quantity
            if (tempItems.get(j).getQuantity() < tempItems.get(j).getMin()) {
                //Only add the category to the list if there's an item shortage in it
                if (!itemShortageFound) {
                    list.add(shoppingCategory);
                    itemShortageFound = true;
                }
                shoppingCategory.items.add(tempItems.get(j));
            }
        }
        Collections.sort(shoppingCategory.items);
    }
    Collections.sort(list);
    return list;
}

public boolean addCategory(String name, Context context)
{
    boolean itemFound = false;
    //Logic that ensures the category isn't already in the list
    for (int i = 0; i < inventory.size(); i++)
    {
        if(inventory.get(i).getCategoryName() == name) {
            itemFound = true;
            Toast.makeText(context, "Category name already in list - Cannot Add", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    if (!itemFound) {
        Category category = new Category();
        category.setCategoryName(name);
        Collections.sort(inventory);
        Toast.makeText(context, "Category successfully added to list", Toast.LENGTH_SHORT).show();
    }
    return true;
}

}
