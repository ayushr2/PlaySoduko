package com.example.playsoduko.playsoduko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ayushranjan on 19/12/16.
 */

public class Win extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_layout);

        final int difficulty = getIntent().getIntExtra("difficulty", 1);

        final Button nextB = (Button) findViewById(R.id.next);
        final TextView textView = (TextView) findViewById(R.id.title);
        final Button newGame = (Button) findViewById(R.id.newGame);
        final Button home = (Button) findViewById(R.id.home);

        textView.setText("Congratulations! Level " + difficulty + " Complete!");
        nextB.setText("Move to difficulty " + (difficulty+1));

        if(difficulty == 5)
            nextB.setVisibility(View.INVISIBLE);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlaySingle.class);
                intent.putExtra("difficulty", (difficulty+1));
                startActivity(intent);
            }
        });

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Play.class));
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            }
        });
    }
}
