package com.example.storagemaster.storagemaster;

/**
 * Created by JBThomas on 2/28/2018.
 */

public class Item {

    private String itemName;
    private int quantity;
    private int min;


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
        this.quantity = startQuantity;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
