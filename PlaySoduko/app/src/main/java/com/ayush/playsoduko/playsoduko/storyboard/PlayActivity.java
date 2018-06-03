package com.ayush.playsoduko.playsoduko.storyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ayush.playsoduko.playsoduko.storyboard.play_mode.SinglePlayerGame;
import com.ayush.playsoduko.playsoduko.utilities.SudokuBoard;
import com.ayush.playsoduko.playsoduko.storyboard.play_mode.MultiPlayerGame;
import com.ayush.playsoduko.playsoduko.R;

/**
 * This activity represents the activity which displays the difficulty difficultySeek bar and the user has the
 * option of playing locally or online with an opponent.
 *
 * @author ayushranjan
 * @see SinglePlayerGame
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
                        difficultyText.setText(String.format("%d/%d", (progress + 1), MAX_DIFFICULTY));
                    }
                }
        );

        localPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SinglePlayerGame.class);
                intent.putExtra(SudokuBoard.DIFFICULTY_TAG, (difficultySeek.getProgress() + 1));
                startActivity(intent);
            }
        });


        onlinePLayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MultiPlayerGame.class);
                intent.putExtra(SudokuBoard.DIFFICULTY_TAG, (difficultySeek.getProgress() + 1) );
                startActivity(intent);
            }
        });
    }

    private void initElements() {
        difficultySeek = findViewById(R.id.difficulty_seek_bar);
        difficultyText = findViewById(R.id.seek_bar_text_view);
        localPlayButton = findViewById(R.id.play_locally_button);
        onlinePLayButton = findViewById(R.id.play_online_button);
    }

    public void onBackPressed() {
        finish();
    }

}