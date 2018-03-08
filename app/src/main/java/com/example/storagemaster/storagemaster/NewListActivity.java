package com.example.storagemaster.storagemaster;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewListActivity extends AppCompatActivity {

    Button saveButton;
    Button deleteButton;

    EditText listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.3));

        // initialize the edit text'
        listName = findViewById(R.id.editText);

        // initialize the buttons
        saveButton = findViewById(R.id.button2);
        deleteButton = findViewById(R.id.button);

        //set on click listeners
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category newCategory = new Category();
                newCategory.setCategoryName(listName.getText().toString());
                MainActivity.inventory.add(newCategory);

                Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
                MainActivity.addNavDrawerItems(menu);

                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //MainActivity.inventory.remove(position);

                Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
                MainActivity.addNavDrawerItems(menu);

                finish();
            }
        });

        //Henry Function for adding a ListName/string to Nav Drawer
        Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
        MainActivity.addNavDrawerItems(menu);

        //System.out.println("Inventory Size: " + MainActivity.inventory.size());




    }

}
