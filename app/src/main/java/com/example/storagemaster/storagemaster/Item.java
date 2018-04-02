package com.example.storagemaster.storagemaster;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by JBThomas on 2/28/2018.
 * The main class that holds all the information about an item
 */

public class Item implements Comparable<Item>{

    private SpannableString quantity;
    private SpannableString itemName;
    private int min;
    /**
     * True if the item quantity has fallen beneath the minimum amount
     * or if the user manually adds it to the shopping list
     */
    public boolean inShoppingList;
    /**
     * True if user presses crossed button in shopping list.
     * Sort moves item to bottom.
     */
    private boolean isCrossed;

    private ArrayList<Barcode> barcodesList = new ArrayList<Barcode>();

    private static final String TAG = "Item";

    Item(){
        itemName = null;
        quantity = new SpannableString("0");
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    /**
     * Item Constructor that sets item name.
     *
     * @param itemName
     */
    Item(String itemName){
        quantity = new SpannableString("0");
        this.itemName = new SpannableString(itemName);
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    /**
     * Item constructor that sets name and quantity.
     *
     * @param itemName
     * @param quantity
     */
    Item(String itemName, int quantity){
        String newQuantity = Integer.toString(quantity);
        this.quantity = new SpannableString(newQuantity);
        this.itemName = new SpannableString(itemName);
        min = -1;
        inShoppingList = false;
        isCrossed = false;
    }

    /**
     * Item constructor that sets name, quantity, and min.
     *
     * @param itemName
     * @param quantity
     * @param min
     */
    Item(String itemName, int quantity, int min){
        this.itemName = new SpannableString(itemName);
        String newQuantity = Integer.toString(quantity);
        this.quantity = new SpannableString(newQuantity);
        this.min = min;
        inShoppingList = false;
        isCrossed = false;
    }

    public void setItemName(String name){
        this.itemName = new SpannableString(name);
    }

    public SpannableString getItemName(){
        if(isCrossed) {
            itemName.setSpan(new StrikethroughSpan(), 0, itemName.length(), 0);
        }
        else{
            itemName = new SpannableString(itemName.toString());
        }
        return itemName;
    }

    public SpannableString getQuantity() {
        if (Integer.parseInt(quantity.toString()) <=  min)
        {
            inShoppingList = true;
            quantity.setSpan(new ForegroundColorSpan(Color.RED), 0, quantity.length(), 0);//change color to RED
            //quantity.setSpan(new BackgroundColorSpan(Color.DKGRAY), 0, quantity.length(), 0);
        }
        else
        {
            inShoppingList = false;
        }
        return quantity;
    }

    public void setQuantity(int startQuantity) {
        if(startQuantity >= 0 & startQuantity < 1000) {
            this.quantity = new SpannableString(Integer.toString(startQuantity));
            if (startQuantity <=  min)
            {
                MainActivity.user.inventory.get(0).addExistingItem(this);
                inShoppingList = true;
                quantity.setSpan(new ForegroundColorSpan(Color.RED), 0, quantity.length(), 0);//change color to RED
            }
            else
            {
                MainActivity.user.inventory.get(0).removeItem(this);
                inShoppingList = false;
            }
        }
    }

    public void addQuantity(){
        /*String number = "10";
        int result = Integer.parseInt(number);*/
        int result = Integer.parseInt(quantity.toString());
        result++;
        quantity = new SpannableString(Integer.toString(result));
        if (result <=  min)
        {
            MainActivity.user.inventory.get(0).addExistingItem(this);
            inShoppingList = true;
        }
        else
        {
            MainActivity.user.inventory.get(0).removeItem(this);
            inShoppingList = false;
        }
    }

    public void subtractQuantity(){
        int result = Integer.parseInt(quantity.toString());
        if(result > 0)
            result--;
        quantity = new SpannableString(Integer.toString(result));
        if (result <=  min)
        {
            MainActivity.user.inventory.get(0).addExistingItem(this);
            inShoppingList = true;
            //quantity.setSpan(new StrikethroughSpan(), 0, quantity.length(), 0);
        }
        else
        {
            MainActivity.user.inventory.get(0).removeItem(this);
            inShoppingList = false;
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
        String codeName1 = this.getItemName().toString().toUpperCase();
        String codeName2 = item.getItemName().toString().toUpperCase();

        //ascending order
        return codeName1.compareTo(codeName2);

        //descending order
        //return codeName2.compareTo(codeName1);
    }
}
