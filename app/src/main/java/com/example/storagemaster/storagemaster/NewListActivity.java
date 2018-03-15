package com.example.storagemaster.storagemaster;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewListActivity extends AppCompatActivity {

    private static final String TAG = "NewListActivity";
    Button saveButton;
    Button deleteButton;

    EditText listName;

    // this variable is to get the correct category from the inventory
    private int catPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.3));
        getWindow().setSoftInputMode(20);
        Log.d(TAG, "changed display size");
        // initialize the edit text'
        listName = findViewById(R.id.editText);

        // initialize the buttons
        saveButton = findViewById(R.id.button2);
        deleteButton = findViewById(R.id.button);

        catPosition = Integer.parseInt(getIntent().getStringExtra(MainActivity.POSC));

        if(catPosition > -1){
           // listName.setText(MainActivity.category.items.get(position).getItemName());
            listName.setText(MainActivity.navigationView.getMenu().getItem(catPosition).getTitle());
            this.setTitle("Edit List");
        }

        //set on click listeners
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu

                if (catPosition > 0) {
                    listName = findViewById(R.id.editText);
                    MainActivity.user.inventory.get(catPosition).setCategoryName(listName.getText().toString());
                }
                else{
                    Category newCategory = new Category();
                    newCategory.setCategoryName(listName.getText().toString());
                    MainActivity.user.inventory.add(newCategory);
                    Log.d(TAG, "list added to inventory");
                }

                //Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
                MainActivity.addNavDrawerItems(menu);

                Log.d(TAG, "addNavDrawerItems called");
                //item.setCheckable(true);
                if(catPosition > -1)
                    menu.getItem(catPosition).setChecked(true);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
                if (catPosition > 0) {
                    MainActivity.user.inventory.remove(catPosition);
                    menu.removeItem(catPosition);
                }

                MainActivity.addNavDrawerItems(menu);

                finish();
            }
        });

        //Henry Function for adding a ListName/string to Nav Drawer
       // Menu menu = MainActivity.navigationView.getMenu(); //access to the nav drawer menu
       // MainActivity.addNavDrawerItems(menu);

        //System.out.println("Inventory Size: " + MainActivity.inventory.size());




    }

}
