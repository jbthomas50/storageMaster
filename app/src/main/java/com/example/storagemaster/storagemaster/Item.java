package com.example.storagemaster.storagemaster;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by JBThomas on 2/28/2018.
 * The main class that holds all the information about an item
 */

public class Item implements Comparable<Item>{

    private String itemName;
    private int quantity;
    private int min;
    /**
     * True if the item quantity has fallen beneath the minimum amount
     * or if the user manually adds it to the shopping list
     */
    public boolean inShoppingList;

    private boolean isCrossed;

    private ArrayList<Barcode> barcodesList = new ArrayList<Barcode>();

    private static final String TAG = "Item";

    Item(){
        itemName = null;
        quantity = 0;
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    Item(String itemName){
        this.itemName = itemName;
        quantity = 0;
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    Item(String itemName, int quantity){
        this.itemName = itemName;
        this.quantity = quantity;
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    Item(String itemName, int quantity, int min){
        this.itemName = itemName;
        this.quantity = quantity;
        this.min = min;
        inShoppingList = false;
        isCrossed = false;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int startQuantity) {
        if(startQuantity >= 0 & startQuantity < 1000) {
            this.quantity = startQuantity;
        }
    }

    public int getMin() {
        return min;
    }

    /**
     * Ensures that the item minimum isn't below 0 or more than 999
     * @param min The minimum amount of the item the user WANTS to have on hand
     */
    public void setMin(int min) {
        if(min >= 0 && min < 1000) {
            this.min = min;
        }
    }

    /**
     * An item can have multiple barcodes if the user buys it from
     * a different store, adds this barcode to the list of barcodes
     * @param barcode The barcode object to add to the list
     */
    public void addBarcode(Barcode barcode)
    {
        barcodesList.add(barcode);
        Collections.sort(barcodesList);
    }

    /**
     * cross out an item from the shopping list.
     */
    public void crossItem(){
        isCrossed = true;
    }

    /**
     * uncross an item that has been crossed
     */
    public void uncrossItem(){
        isCrossed = false;
    }

    /**
     * return if the item has been crossed from the shopping list.
     *
     * @return isCrossed
     */
    public boolean getCrossed(){
        return isCrossed;
    }

    /**
     * Used to sort the list of items in the CATEGORY object
     * @param item The item to be sorted
     * @return Returns true if the item has a higher sort value
     */
    @Override
    public int compareTo(@NonNull Item item) {

        //Sorting by name
        String codeName1 = this.getItemName().toUpperCase();
        String codeName2 = item.getItemName().toUpperCase();

        //ascending order
        return codeName1.compareTo(codeName2);

        //descending order
        //return codeName2.compareTo(codeName1);
    }
}
