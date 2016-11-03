package com.example.playsoduko.playsoduko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button playB = (Button)findViewById(R.id.playButton);
        Button solveB = (Button)findViewById(R.id.solveButton);

        final Intent intentPlay = new Intent(this,Play.class);
        final Intent intentSolve = new Intent(this,Solve.class);

        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPlay);
            }
        });
        solveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSolve);
            }
        });

    }
}


