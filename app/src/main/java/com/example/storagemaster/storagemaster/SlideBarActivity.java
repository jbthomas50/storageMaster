package com.example.storagemaster.storagemaster;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * SLIDE BAR ACTIVITY created by James
 * creates a slider bar to change the number of items
 * in the inventory.
 */
public class SlideBarActivity extends AppCompatActivity{

    private static final String TAG = "SlideBarActivity";
    private int seekValue;
    private SeekBar seek;
    private EditText numItems;

    private Button save;
    private Button more;
    private Button less;

    private int itemPosition;
    private int catPosition;

    /**
     * Set up UI for Slide Bar.
     *
     * @param savedInstanceState is stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_bar);

        // JAMES - gets the size of the screen and then sets it to the desired size.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        //initialize the variables.
        this.itemPosition = Integer.parseInt(getIntent().getStringExtra(MainActivity.POSI));
        this.catPosition = Integer.parseInt(getIntent().getStringExtra(MainActivity.POSC));
        this.setTitle(MainActivity.user.inventory.get(catPosition).items.get(itemPosition).getItemName());

        seekValue = MainActivity.user.inventory.get(catPosition).items.get(itemPosition).getQuantity();

        seek = new SeekBar(this);
        seek = findViewById(R.id.seekBar);
        seek.setProgress(seekValue);



        numItems = new EditText(this);
        numItems = findViewById(R.id.numItems);

        numItems.setText("" + seekValue);

        more = findViewById(R.id.moreButton);
        less = findViewById(R.id.lessButton);
        save = findViewById(R.id.sliderSave);

        // set up the more button
        more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                seekValue++;
                numItems.setText("" + seekValue);
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        // set up the less button
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekValue--;
                numItems.setText("" + seekValue);
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        numItems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                seekValue = Integer.parseInt(numItems.getText().toString());
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        //set up the seek bar. Needs to set the value in the text box equal to the value of the bar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekValue = i;
                numItems.setText("" + seekValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set seek value and change the item's quantity equal to it
                seekValue = Integer.parseInt(numItems.getText().toString());
                Log.d(TAG, "got seek value");
                MainActivity.user.inventory.get(catPosition).items.get(itemPosition).setQuantity(seekValue);//user.inventory.get(0).items.get(position).setQuantity(seekValue);

                //Henry- set shopping list to true or false based off of the min and quantity
                //changes the color of quantity to red if in the shopping list
                ///THIS IS NOT WORKING!!!!
                if (seekValue <= MainActivity.user.inventory.get(catPosition).items.get(itemPosition).getMin()){
                    MainActivity.user.inventory.get(catPosition).items.get(itemPosition).inShoppingList = true;
                    //quantityView.setTextColor(Color.RED);
                    ItemListAdapter.viewHolder.qty.setTextColor(Color.RED);
                }
                else{
                    //quantityView.setTextColor(Color.GRAY);
                    ItemListAdapter.viewHolder.qty.setTextColor(Color.GRAY);
                    MainActivity.user.inventory.get(catPosition).items.get(itemPosition).inShoppingList = false;
                }

                Log.d(TAG, "set quantity");
                if(catPosition > 0) {
                    MainActivity.adapter.notifyDataSetChanged();
                }
                else{
                    MainActivity.adapterShopping.notifyDataSetChanged();
                }
                Log.d(TAG, "notified adapter");
                finish();
            }
        });
    }
}
