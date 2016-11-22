package com.example.playsoduko.playsoduko;

/**
 * Created by ayushranjan on 30/10/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Play extends AppCompatActivity {

    Button currentlySelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
        final Button oneDif = (Button) findViewById(R.id.one);
        final Button twoDif = (Button) findViewById(R.id.two);
        final Button threeDif = (Button) findViewById(R.id.three);
        final Button fourDif = (Button) findViewById(R.id.four);
        final Button fiveDif = (Button) findViewById(R.id.five);

        oneDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneDif.setPressed(true);
                if(currentlySelected != null)
                    currentlySelected.setPressed(false);
                currentlySelected = oneDif;
            }
        });
        twoDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoDif.setPressed(true);
                if(currentlySelected != null)
                    currentlySelected.setPressed(false);
                currentlySelected = twoDif;
            }
        });
        threeDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threeDif.setPressed(true);
                if(currentlySelected != null)
                    currentlySelected.setPressed(false);
                currentlySelected = threeDif;
            }
        });
        fourDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourDif.setPressed(true);
                if(currentlySelected != null)
                    currentlySelected.setPressed(false);
                currentlySelected = fourDif;
            }
        });
        fiveDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveDif.setPressed(true);
                if(currentlySelected != null)
                    currentlySelected.setPressed(false);
                currentlySelected = fiveDif;
            }
        });

    }
}