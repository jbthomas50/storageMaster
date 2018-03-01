package com.example.storagemaster.storagemaster;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by JBThomas on 2/28/2018.
 */


public class NewItemTest {

    @Test
    public void testNewItem(){
        Item item = new Item();
        String str= "beans";
        item.setItemName(str);

        assertEquals(str, item.getItemName());
    }

    @Test
    public void testStartQuantity(){
        Item item = new Item();
        int start = 3;
        item.setStartQuantity(3);

        assertEquals(start, item.getStartQuantity());
    }

    @Test
    public void testMinimum(){
        Item item = new Item();
        int min = 1;
        item.setMin(min);

        assertEquals(min, item.getMin());
    }
}
