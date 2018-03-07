package com.example.storagemaster.storagemaster;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.Toast;

public class NewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.3));

        Category newCategory = new Category();
        newCategory.setCategoryName("newList " + MainActivity.inventory.size());
        MainActivity.inventory.add(newCategory);

        //Henry Function for adding a ListName/string to Nav Drawer
        Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
        MainActivity.addNavDrawerItems(menu);

        //System.out.println("Inventory Size: " + MainActivity.inventory.size());


    }

}
