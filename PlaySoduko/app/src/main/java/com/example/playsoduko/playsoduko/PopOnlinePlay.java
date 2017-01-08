package com.example.playsoduko.playsoduko;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by ayushranjan on 08/01/17.
 */
public class PopOnlinePlay extends Activity{
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
        Button num3 = (Button) findViewById(R.id.three);
        Button num4 = (Button) findViewById(R.id.four);
        Button num5 = (Button) findViewById(R.id.five);
        Button num6 = (Button) findViewById(R.id.six);
        Button num7 = (Button) findViewById(R.id.seven);
        Button num8 = (Button) findViewById(R.id.eight);
        Button num9 = (Button) findViewById(R.id.nine);
        Button num0 = (Button) findViewById(R.id.zero);

        String buttonDissapear = Soduko.buttonsNotAvailable(PlayOnline.soduko,PlayOnline.i,PlayOnline.j);

        for(int i = 0; i < buttonDissapear.length(); i++){
            switch (buttonDissapear.charAt(i)){
                case '1':
                    num1.setVisibility(View.INVISIBLE);
                    break;
                case '2':
                    num2.setVisibility(View.INVISIBLE);
                    break;
                case '3':
                    num3.setVisibility(View.INVISIBLE);
                    break;
                case '4':
                    num4.setVisibility(View.INVISIBLE);
                    break;
                case '5':
                    num5.setVisibility(View.INVISIBLE);
                    break;
                case '6':
                    num6.setVisibility(View.INVISIBLE);
                    break;
                case '7':
                    num7.setVisibility(View.INVISIBLE);
                    break;
                case '8':
                    num8.setVisibility(View.INVISIBLE);
                    break;
                case '9':
                    num9.setVisibility(View.INVISIBLE);
                    break;
                default:
                    break;
            }
        }


        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 1;
                PlayOnline.update();
                finish();
            }
        });


        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 2;
                PlayOnline.update();
                finish();
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 3;
                PlayOnline.update();
                finish();
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 4;
                PlayOnline.update();
                finish();
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 5;
                PlayOnline.update();
                finish();
            }
        });


        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 6;
                PlayOnline.update();
                finish();
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 7;
                PlayOnline.update();
                finish();
            }
        });


        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 8;
                PlayOnline.update();
                finish();
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 9;
                PlayOnline.update();
                finish();
            }
        });


        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOnline.CURRENT_NUMBER = 0;
                PlayOnline.update();
                finish();
            }
        });
    }
}