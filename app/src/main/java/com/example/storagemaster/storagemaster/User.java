package com.example.storagemaster.storagemaster;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by ME on 3/2/2018.
 */

public class User {
    private String name;
    private String password;
    public ArrayList<Category> inventory;

    public User(){
        name = "";
        password = "";
        inventory = new ArrayList<>();
    }

    public ArrayList<Category> getShoppingList() {
        ArrayList<Category> list = new ArrayList<Category>(); //Main list that we'll return
        //Searching through the categories
        for (int i = 0; i < inventory.size(); i++) {
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

    public boolean addCategory(String name, Context context) {
        boolean itemFound = false;
        //Logic that ensures the category isn't already in the list
        for (int i = 0; i < inventory.size(); i++) {
            if (Objects.equals(inventory.get(i).getCategoryName(), name)) {
                itemFound = true;
                Toast.makeText(context, "Category name already in list - Cannot Add", Toast.LENGTH_SHORT).show();
                Log.i("User", "User attempted to make duplicate category: " + name);
                return false;
            }
        }
        if (!itemFound) {
            Category category = new Category();
            category.setCategoryName(name);
            Collections.sort(inventory);
            Toast.makeText(context, "Category successfully added to list", Toast.LENGTH_SHORT).show();
            Log.i("User", "User added a valid category: " + name);
        }
        return true;
    }

    public boolean removeCategory(String name, Context context) {

        boolean itemFound = false;
        //Logic that searches for the item in the list
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getCategoryName() == name) {
                inventory.remove(inventory.get(i));
                itemFound = true;
                Toast.makeText(context, "Item successfully removed", Toast.LENGTH_SHORT).show();
                Log.i("User", "User successfully removed category: " + name);
                return true;
            }
        }
        if (!itemFound) {
            Toast.makeText(context, "Item not found", Toast.LENGTH_SHORT).show();
            Log.e("User", "User attempted to remove an item that doesn't exist: " + name);
        }
        return false;
    }

    public boolean moveItem(String name, String categoryFrom, String categoryTo, Context context) {
        boolean itemFound = false;
        Category cFrom = null;
        Category cTo;
        //Logic that searches for the item in the list
        for (int i = 0; i < inventory.size() && !itemFound; i++) {
            if (inventory.get(i).getCategoryName() == categoryFrom) {
                cFrom = inventory.get(i);
                itemFound = true;
            }
        }

        if (!itemFound) {
            Toast.makeText(context, "1st category not found", Toast.LENGTH_SHORT).show();
            Log.e("User", "User attempted to move an item from a category that doesn't exist: " + categoryFrom);
            return false;
        }

        itemFound = false;
        for (int i = 0; i < inventory.size() && !itemFound; i++) {
            if (inventory.get(i).getCategoryName() == categoryTo) {
                cTo = inventory.get(i);
                itemFound = true;
            }
        }
        if (!itemFound) {
            Toast.makeText(context, "2nd category not found", Toast.LENGTH_SHORT).show();
            Log.e("User", "User attempted to move an item to a category that doesn't exist: " + categoryTo);
            return false;
        }

        itemFound = false;

        for (int i = 0; i < cFrom.items.size() && !itemFound; i++) {
            if (cFrom.items.get(i).getItemName() == name) {
                cFrom = inventory.get(i);
                itemFound = true;
            }
        }
        if (!itemFound) {
            Toast.makeText(context, "Item not found", Toast.LENGTH_SHORT).show();
            Log.e("User", "User attempted to move an item that doesn't exist: " + name);
            return false;
        }

        return true;
    }

}
