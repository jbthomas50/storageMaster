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

    public boolean removeItem(String name, Context context){
        boolean itemFound = false;
        //Logic that searches for the item in the list
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getItemName() == name) {
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
