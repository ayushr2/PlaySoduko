package com.example.playsoduko.playsoduko;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ayushranjan on 24/11/16.
 */
public class PlaySingle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int difficulty = getIntent().getIntExtra("difficulty",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_single);

    }
}
