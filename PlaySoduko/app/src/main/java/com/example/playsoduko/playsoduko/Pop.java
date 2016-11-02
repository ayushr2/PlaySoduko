package com.example.playsoduko.playsoduko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;


import java.io.Serializable;

/**
 * Created by ayushranjan on 01/11/16.
 */
public class Pop extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_pad);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int newWidth = (int) (dm.widthPixels*0.7);
        int newHeight = (int) (dm.heightPixels*0.7);

        getWindow().setLayout(newWidth,newHeight);

        Button num1 = (Button) findViewById(R.id.one);
        Button num2 = (Button) findViewById(R.id.two);

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Solve.CURRENT_NUMBER = 1;
                Solve.changeNumber();
                finish();
            }
        });


        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Solve.CURRENT_NUMBER = 2;
                Solve.changeNumber();
                finish();
            }
        });
    }
}
