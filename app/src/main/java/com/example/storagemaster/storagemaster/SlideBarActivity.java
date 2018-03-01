package com.example.storagemaster.storagemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SlideBarActivity extends AppCompatActivity {

    int seekValue = 0;
    SeekBar seek;
    EditText numItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_bar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        seek = new SeekBar(this);
        numItems = new EditText(this);

        seek = findViewById(R.id.seekBar);
        numItems = findViewById(R.id.numItems);

        Button more = findViewById(R.id.moreButton);
        Button less = findViewById(R.id.lessButton);

        more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                seekValue++;
                numItems.setText("" + seekValue);
            }
        });

        less.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                seekValue--;
                numItems.setText("" + seekValue);
            }
        });

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
    }
}
