package com.example.storagemaster.storagemaster;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.util.Collections;
import java.util.Comparator;

/**
 * Popup for creating a new item or editing an existing item.
 *
 * This popup has several parts to it. There are three editable
 * text boxes (name, quantity, minimum), and two buttons(save,
 * delete). The three text boxes will have default values if a
 * new item is being created, or will load the information from
 * an existing item if it is being edited.
 *
 */
public class NewItem extends AppCompatActivity {

    /**Tag used for log entries.*/
    public static final String TAG = "NewItem";
    /**First box in the popup. For item name.*/
    EditText nameText;
    /**Second box in popup. For quantity of item.*/
    EditText quantityText;
    /**Third box in popup. For user's desired minimum amount.*/
    EditText minText;

    /**Save new/edited item*/
    Button saveButton;
    /**Delete existing item*/
    Button deleteButton;
    /**for correct item from the itemlist*/
    private int itemNum;
    /**for correct category from the user*/
    private int categoryNum;

    @Override
    protected void onStop() {
        super.onStop();
        MainActivity.isWindowOpen = false;
    }

    /**
     * Set up UI for New Item Activity.
     *
     * @param savedInstanceState
     */



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // set the window to the correct size.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.8)); //Originally .8 .45

        getWindow().setSoftInputMode(5|20);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.50f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);

        // initialize the text boxes
        nameText = findViewById(R.id.newItemText);
        quantityText = findViewById(R.id.startQuantityText);
        minText = findViewById(R.id.minimumText);

        // if they are editing an already existing item we need to load the values into the text
        // boxes
        itemNum = Integer.parseInt(getIntent().getStringExtra(MainActivity.POSI));
        categoryNum = Integer.parseInt(getIntent().getStringExtra(MainActivity.POSC));
        if(itemNum >= 0){
            nameText.setText(MainActivity.user.inventory.get(categoryNum).items.get(itemNum).getItemName().toString());
            quantityText.setText(MainActivity.user.inventory.get(categoryNum).items.get(itemNum).getQuantity());
            minText.setText(Integer.toString(MainActivity.user.inventory.get(categoryNum).items.get(itemNum).getMin()));
            this.setTitle("Edit Item");
        }

        // initialize the buttons
        saveButton = findViewById(R.id.saveItemButton);
        deleteButton = findViewById(R.id.deleteItemButton);
        

        /**
         * Get the values from the three text boxes, save them to the item
         * and then end the activity.
         */
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Save clicked");
                int quantityI = 1;
                int minI = 0;
                String nameI = ("" + nameText.getText());
                // add the new values to the item from the text boxes.
                String quantity = quantityText.getText().toString();
                if(TextUtils.isDigitsOnly(quantity) && quantityText.getText().length() > 0) {
                    quantityI = Integer.parseInt(quantity);
                }
                String min = minText.getText().toString();
                if(TextUtils.isDigitsOnly(min) && minText.getText().length() > 0) {
                    minI = Integer.parseInt(min);
                }
                Log.d(TAG, " " + MainActivity.user.inventory.get(0).items.size() + " ");
                if(itemNum >= 0){
                    MainActivity.user.inventory.get(categoryNum).items.get(itemNum).setItemName(nameI);
                    MainActivity.user.inventory.get(categoryNum).items.get(itemNum).setMin(minI);
                    MainActivity.user.inventory.get(categoryNum).items.get(itemNum).setQuantity(quantityI);
                }
                else {
                    MainActivity.user.inventory.get(categoryNum).addItem(nameI, quantityI, minI);
                }
                if(categoryNum > 0) {
                    MainActivity.adapter.notifyDataSetChanged();
                }
                else{
                    Collections.sort(MainActivity.user.inventory.get(categoryNum).items, new ShoppingCompare());
                    MainActivity.adapterShopping.notifyDataSetChanged();
                }

                finish();
            }
        });

        /**
         * Delete the item.
         *
         * Ff there is an item already there, it will be deleted. If this
         * button is pushed without a pre-existing item, it will just end
         * the activity.
         */
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(itemNum >= 0) {
                    MainActivity.user.inventory.get(categoryNum).items.remove(itemNum);
                    if(categoryNum > 0) {
                        MainActivity.adapter.notifyDataSetChanged();
                    }
                    else{
                        MainActivity.adapterShopping.notifyDataSetChanged();
                    }
                }
                finish();
            }
        });
    }
}
