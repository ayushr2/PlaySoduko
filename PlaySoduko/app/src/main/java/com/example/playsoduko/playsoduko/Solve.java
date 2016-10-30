package com.example.playsoduko.playsoduko;

/**
 * Created by ayushranjan on 30/10/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Solve extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_layout);


        final Button b00 = (Button)findViewById(R.id.index00);
        b00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }
}