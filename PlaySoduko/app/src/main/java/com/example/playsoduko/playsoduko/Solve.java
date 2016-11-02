package com.example.playsoduko.playsoduko;

/**
 * Created by ayushranjan on 30/10/16.
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;

public class Solve extends AppCompatActivity{
    public static int CURRENT_NUMBER = 0;
    public static Button CURRENT_BUTTON = null;

    public static void changeNumber(){
        if(CURRENT_BUTTON != null){
            CURRENT_BUTTON.setText("" + CURRENT_NUMBER);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_layout);

        final Button b00 = (Button)findViewById(R.id.index00);
        final Button b01 = (Button)findViewById(R.id.index01);

        b00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b00;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b01;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

    }
}