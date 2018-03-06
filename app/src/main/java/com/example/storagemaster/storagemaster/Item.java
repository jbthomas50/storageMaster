package com.example.storagemaster.storagemaster;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by JBThomas on 2/28/2018.
 */

public class Item implements Comparable<Item>{

    private String itemName;
    private int quantity;
    private int min;
    private ArrayList<Barcode> barcodesList = new ArrayList<Barcode>();

    Item(){
        itemName = null;
        quantity = 0;
        min = -1;
    }

    Item(String itemName){
        this.itemName = itemName;
        quantity = 0;
        min = -1;
    }

    Item(String itemName, int quantity){
        this.itemName = itemName;
        this.quantity = quantity;
        min = -1;
    }

    Item(String itemName, int quantity, int min){
        this.itemName = itemName;
        this.quantity = quantity;
        this.min = min;
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
        if(startQuantity >= 0) {
            this.quantity = startQuantity;
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        if(min >= 0) {
            this.min = min;
        }
    }

    public void addBarcode(Barcode barcode)
    {
        barcodesList.add(barcode);
        Collections.sort(barcodesList);
    }

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
