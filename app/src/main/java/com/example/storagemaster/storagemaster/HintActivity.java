package com.example.storagemaster.storagemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Simple activity to display hints on use of app.
 */
public class HintActivity extends AppCompatActivity {

    /**
     * No logic is needed. Simply displays some text.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
    }
}
