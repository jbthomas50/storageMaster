package com.example.storagemaster.storagemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItem extends AppCompatActivity {

    // text boxes
    EditText nameText;
    EditText startQuantityText;
    EditText minText;

    // save button
    Button saveButton;

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
        startQuantityText = findViewById(R.id.startQuantityText);
        minText = findViewById(R.id.minimumText);

        // initialize the button
        saveButton = findViewById(R.id.saveButton);

        // save button will get the values from the three text boxes, save them to the new item
        // and then end the activity.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // create a new item that will be added to the list.
                Item newItem;
                // initialize the new item.
                newItem = new Item();

                // add the new values to the item from the text boxes.
                newItem.setItemName("" + nameText.getText());
                String quantity = startQuantityText.getText().toString();
                if(TextUtils.isDigitsOnly(quantity) && startQuantityText.getText().length() > 0) {
                    newItem.setQuantity(Integer.parseInt(quantity));
                }

                String min = minText.getText().toString();
                if(TextUtils.isDigitsOnly(min) && minText.getText().length() > 0) {
                    newItem.setMin(Integer.parseInt(min));
                }

                MainActivity.itemList.add(newItem);
                MainActivity.adapter.notifyDataSetChanged();

                finish();
            }
        });
    }
}
