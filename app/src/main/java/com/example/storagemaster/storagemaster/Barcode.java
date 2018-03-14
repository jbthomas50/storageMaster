package com.example.storagemaster.storagemaster;

import android.support.annotation.NonNull;

/**
 * Created by ME on 3/2/2018.
 */

public class Barcode implements Comparable<Barcode> {
    private String name;
    private int value;
    private static final String TAG = "Barcode";

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(@NonNull Barcode barcode) {

        //Sorting by name
        String codeName1 = this.getName().toUpperCase();
        String codeName2 = barcode.getName().toUpperCase();

        //ascending order
        return codeName1.compareTo(codeName2);

        //descending order
        //return codeName2.compareTo(codeName1);

    }
}
