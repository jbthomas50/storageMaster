package com.example.storagemaster.storagemaster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by Alex the one and only on 3/1/2018.
 * Mainly to hold an ArrayList of items for the user
 * A category is meant to hold a list of similar items
 * e.g. food, clothes, cleaning supplies.
 */
public class Category implements Comparable<Category> {

    private String categoryName;
    protected ArrayList<Item> items = new ArrayList<Item>(); //DO NOT MANUALLY ADD ITEMS, use addItem() instead

    private static final String TAG = "Category";
    public void setCategoryName(String name){
        this.categoryName = name;
    }

    public String getCategoryName(){
        return categoryName;
    }

    /**
     * This class takes care of sorting the list of items and checking that the user
     * isn't adding a duplicate item
     * @param name What the user wants to call the item
     * @param amount The amount of that item the user has on hand
     * @param minimumAmount The amount of that item the user WANTS to always have on hand
     * @return Returns true if the item was successfully added to the list
     */
    public boolean addItem(String name, int amount, int minimumAmount) {
        boolean itemFound = false;
        //Logic that ensures the item isn't already in the list
        for (int i = 0; i < items.size(); i++)
        {
            if(Objects.equals(items.get(i).getItemName(), name)) {
                itemFound = true;
                Log.i("Category", "User attempted to add a duplicate item: " + name);
                //Toast.makeText(context, "Item name already in list - Cannot Add", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (!itemFound) {
            Item item = new Item();
            item.setItemName(name);
            int amountNew = amount;
            item.setQuantity(amountNew);
            item.setMin(minimumAmount);
            //item.addBarcode(barcode);
            items.add(item);
            Collections.sort(items);
            //Toast.makeText(context, "Item successfully added to list", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    /**
     * Looks for the name of the item in the list and removes it if it's there
     * @param name The name of the item the user wants to remove from the list
     * @param context The context the app is in so it knows how to display a toast
     * @return Returns true if it was successfully removed
     */
    public boolean removeItem(String name, Context context){
        boolean itemFound = false;
        //Logic that searches for the item in the list
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getItemName().toString() == name) {
                items.remove(items.get(i));
                itemFound = true;
                Toast.makeText(context, "Item successfully removed", Toast.LENGTH_SHORT).show();
                Log.i("Category", "User successfully removed item: " + name);
                return true;
            }
        }
        if (!itemFound) {
            Toast.makeText(context, "Item not found", Toast.LENGTH_SHORT).show();
            Log.e("Category", "User attempted to remove an item that doesn't exist: " + name);
        }
        return false;
    }

    /**
     * cross out an item from the shopping list.
     */
    public void crossItem(int pos){
        items.get(pos).crossItem();
        Collections.sort(items, new ShoppingCompare());
    }

    /**
     * uncross an item that has been crossed
     */
    public void uncrossItem(int pos){
        items.get(pos).uncrossItem();
        Collections.sort(items, new ShoppingCompare());
    }

    /**
     * Used to sort the list of categories in the USER object
     * @param category The category to be compared
     * @return Returns true if the category has a higher sort value
     */
    @Override
    public int compareTo(@NonNull Category category) {

        //Sorting by name
        String codeName1 = this.getCategoryName().toUpperCase();
        String codeName2 = category.getCategoryName().toUpperCase();

        //ascending order
        return codeName1.compareTo(codeName2);

        //descending order
        //return codeName2.compareTo(codeName1);
    }

}
