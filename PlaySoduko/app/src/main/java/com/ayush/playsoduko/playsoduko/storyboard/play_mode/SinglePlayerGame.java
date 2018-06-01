package com.ayush.playsoduko.playsoduko.storyboard.play_mode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.ayush.playsoduko.playsoduko.R;
import com.ayush.playsoduko.playsoduko.utilities.SudokuBoard;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;

/**
 * This activity represents the interface where the user can play locally. This activity extends
 * SudokuBoard which describes a "Generic" Sudoku interface used in this app. I have removed and
 * modified elements from it so it suits the purpose.
 *
 * @author ayushranjan
 * @see SudokuBoard
 * @since 20/04/17.
 */
public class SinglePlayerGame extends SudokuBoard {
    // instance variables
    protected TextView timerTextView;
    protected int cellsToDrop;

    private int difficulty;
    private int gameDuration;
    private CountDownTimer countDownTimer;

    /**
     * This method initialises all UI elements and sets the correct button listeners. Makes all the
     * top bar elements invisible because it is not needed.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseComponents();
        startNewGame();
    }

    /**
     * Initialises UI components and sets up on click listeners
     */
    private void initialiseComponents() {
        timerTextView = findViewById(R.id.timer);
        setDifficulty(getIntent().getIntExtra(SudokuBoard.DIFFICULTY_TAG, 3));

        setUpButtons();
    }

    /**
     * Sets appropriate Button listeners. Have made this a method so that its child class can
     * override it.
     */
    protected void setUpButtons() {
        positiveButton.setText(R.string.new_game);
        negativeButton.setText(R.string.quit);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Displays a Dialog which declares the user as the winner.
     */
    @Override
    protected void showWinDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.won_message));

        if (difficulty < 5) {
            builder.setPositiveButton("Next Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setDifficulty(++difficulty);
                    startNewGame();
                    dialog.cancel();
                }
            });
        }

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * Displays a Dialog which declares the user as the loser.
     */
    @Override
    protected void showLoseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.lost_message));

        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNewGame();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * Starts a new game with a newly generated Sudoku board
     */
    protected void startNewGame() {
        sudoku = new Sudoku(cellsToDrop);
        startGame();
    }

    /**
     * Starts the game with the given sudoku board
     */
    protected void startGame() {
        resetBackground();
        setStartPosition();
        fillGrid();
        freezeGrid();
        startTimer();
    }

    /**
     * Starts the timer text view with the input gameDuration.
     */
    protected void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(gameDuration * COUNT_DOWN_INTERVAL, COUNT_DOWN_INTERVAL) {

            public void onTick(long millisUntilFinished) {
                long seconds = (millisUntilFinished / 1000);
                long minutes = seconds / 60;
                timerTextView.setText(minutes + " : " + (seconds % 60));
                if (sudoku.getNumLeft() == 0) {
                    showWinDialog();
                    countDownTimer.cancel();
                }
            }

            public void onFinish() {
                timerTextView.setText("Time Up!");
                showLoseDialog();
            }
        }.start();
    }

    /**
     * Based on the difficulty value passed in, sets the timer and the number of cells to be dropped.
     *
     * @param newDifficulty the new difficulty to set
     */
    protected void setDifficulty(int newDifficulty) {
        difficulty = newDifficulty;

        switch (difficulty) {
            case 1:
                gameDuration = 2280;
                cellsToDrop = 36;
                break;
            case 2:
                gameDuration = 2160;
                cellsToDrop = 36 + (int) (Math.random() * 5);
                break;
            case 3:
                gameDuration = 2040;
                cellsToDrop = 41 + (int) (Math.random() * 4);
                break;
            case 4:
                gameDuration = 1920;
                cellsToDrop = 45 + (int) (Math.random() * 3);
                break;
            case 5:
                gameDuration = 1800;
                cellsToDrop = 48 + (int) (Math.random() * 3);
                break;
            default:
                gameDuration = 1800;
                cellsToDrop = 40;
                break;
        }
    }
}