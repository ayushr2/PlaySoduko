package com.ayush.playsoduko.playsoduko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This activity represents the activity which displays the difficulty difficultySeek bar and the user has the
 * option of playing locally or online with an opponent.
 *
 * @author ayushranjan
 * @see PlayLocalActivity
 * @since 30/10/16.
 */
public class PlayActivity extends Activity {
    private SeekBar difficultySeek;
    private TextView difficultyText;
    private Button localPlayButton;
    private Button onlinePLayButton;
    public static final int MAX_DIFFICULTY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
        initElements();

        difficultySeek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        difficultyText.setText((progress + 1) + "/" + MAX_DIFFICULTY);
                    }
                }
        );

        localPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayLocalActivity.class);
                intent.putExtra(GridActivity.DIFFICULTY_TAG, (difficultySeek.getProgress() + 1));
                startActivity(intent);
            }
        });


        onlinePLayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayOnlineActivity.class);
                intent.putExtra(GridActivity.DIFFICULTY_TAG, (difficultySeek.getProgress() + 1) );
                startActivity(intent);
            }
        });
    }

    private void initElements() {
        difficultySeek = (SeekBar) findViewById(R.id.difficulty_seek_bar);
        difficultyText = (TextView) findViewById(R.id.seek_bar_text_view);
        localPlayButton = (Button) findViewById(R.id.play_locally_button);
        onlinePLayButton = (Button) findViewById(R.id.play_online_button);
    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

}