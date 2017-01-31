package com.ayush.playsoduko.playsoduko;

/**
 * Created by ayushranjan on 30/10/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Play extends Activity {
    private static SeekBar seek;
    private static TextView seekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
        seek = (SeekBar)findViewById(R.id.seekBar);
        seekText = (TextView)findViewById(R.id.seekBarText);
        seekText.setText((seek.getProgress()+1) + "/" + (seek.getMax()+1));
        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                        seekText.setText((progress+1) + "/" + (seek.getMax()+1));
                    }
                }
        );

        Button playSingle = (Button)findViewById(R.id.playsingle);
        playSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaySingle.class);
                intent.putExtra("difficulty", (seek.getProgress() + 1) );
                startActivity(intent);
                finish();
            }
        });

        /*
        Button onlineB = (Button)findViewById(R.id.playonline);
        onlineB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayOnline.class);
                intent.putExtra("difficulty", (seek.getProgress() + 1) );
                startActivity(intent);
                finish();
            }
        });
        */
    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

}