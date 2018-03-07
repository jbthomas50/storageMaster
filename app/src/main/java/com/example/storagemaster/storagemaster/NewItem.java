package com.example.storagemaster.storagemaster;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItem extends AppCompatActivity {

    // text boxes
    EditText nameText;
    EditText quantityText;
    EditText minText;

    // save and delete button
    Button saveButton;
    Button deleteButton;
    // this variable is to get the correct item from the itemlist
    private int position;

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
        getWindow().setLayout((int)(width*.8), (int)(height*.45));

        // initialize the text boxes
        nameText = findViewById(R.id.newItemText);
        quantityText = findViewById(R.id.startQuantityText);
        minText = findViewById(R.id.minimumText);

        // if they are editing an already existing item we need to load the values into the text
        // boxes
        position = Integer.parseInt(getIntent().getStringExtra(MainActivity.POS));
        if(position >= 0){
            nameText.setText(MainActivity.category.items.get(position).getItemName());
            quantityText.setText(Integer.toString(MainActivity.category.items.get(position).getQuantity()));
            minText.setText(Integer.toString(MainActivity.category.items.get(position).getMin()));
        }

        // initialize the button
        saveButton = findViewById(R.id.saveItemButton);
        deleteButton = findViewById(R.id.deleteItemButton);

        // save button will get the values from the three text boxes, save them to the new item
        // and then end the activity.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int quantityI = 0;
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

                if(position >= 0){
                    MainActivity.category.items.get(position).setItemName(nameI);
                    MainActivity.category.items.get(position).setQuantity(quantityI);
                    MainActivity.category.items.get(position).setMin(minI);

                }
                else {
                    MainActivity.category.addItem(nameI, quantityI, minI);
                }
                MainActivity.adapter.notifyDataSetChanged();

                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                MainActivity.adapter.notifyDataSetChanged();
            }
        });
    }
}
