package com.ayush.playsoduko.playsoduko.storyboard.play_mode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.ayush.playsoduko.playsoduko.R;
import com.ayush.playsoduko.playsoduko.utilities.GridActivity;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;

/**
 * This activity represents the interface where the user can play locally. This activity extends
 * GridActivity which describes a "Generic" Sudoku interface used in this app. I have removed and
 * modified elements from it so it suits the purpose.
 *
 * @author ayushranjan
 * @see GridActivity
 * @since 20/04/17.
 */
public class PlayLocalActivity extends GridActivity {
    // instance variables
    protected TextView timerTextView;
    private CountDownTimer countDownTimer;
    protected int difficulty;
    protected int seconds;
    protected int numOfCellDrop;

    /**
     * This method initialises all UI elements and sets the correct button listeners. Makes all the
     * top bar elements invisible because it is not needed.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positiveButton.setText(R.string.new_game);
        negativeButton.setText(R.string.quit);

        timerTextView = (TextView) findViewById(R.id.timer);
        difficulty = getIntent().getIntExtra(GridActivity.DIFFICULTY_TAG, 3);
        analyseDifficulty(difficulty);

        initSudokuBoard();
        setButtonListeners();
    }

    @Override
    protected void onKeyPressed() {
        // do nothing here
    }

    /**
     * Sets appropriate Button listeners. Have made this a method so that its child class can
     * override it.
     */
    protected void setButtonListeners() {
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                initSudokuBoard();
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
                    difficulty++;
                    analyseDifficulty(difficulty);
                    resetBackground();
                    initSudokuBoard();
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
                resetBackground();
                initSudokuBoard();
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
     * Resets the background to unselected cells without text. Also makes all cells "selectable".
     */
    @Override
    protected void resetBackground() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                cells[x][y].setBackground(getDrawable(R.drawable.unselected_cell));
                cells[x][y].setClickable(true);
            }
        }
    }

    /**
     * Helper functions which calls a bunch of helper functions which refreshes the whole game.
     */
    protected void initSudokuBoard() {
        sudoku = new Sudoku(numOfCellDrop);
        startTimer(seconds);
        fillGrid();
        immutateFeed();
        setCurrentXY();
        updateKeyBoard();
    }

    /**
     * Selects the first empty cell as the starting point in the game.
     */
    protected void setCurrentXY() {
        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                if (sudoku.getCell(i, j) == 0) {
                    currentX = i;
                    currentY = j;
                    selectCurrent();
                    return;
                }
            }
        }
    }

    /**
     * Starts the timer text view with the input seconds.
     *
     * @param seconds the number of seconds the timer is supposed to start with
     */
    protected void startTimer(long seconds) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(seconds * COUNT_DOWN_INTERVAL, COUNT_DOWN_INTERVAL) {

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
     * Makes all the cells which are already filled "unselectable". Also gives them a grey background
     * so its easier for users to infer.
     */
    protected void immutateFeed() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                if (sudoku.getCell(x, y) != Sudoku.EMPTY_VALUE) {
                    cells[x][y].setBackground(getDrawable(R.drawable.filled_cell));
                    cells[x][y].setClickable(false);
                }
            }
        }
    }

    /**
     * Based on the difficulty value passed in, sets the timer and the number of cells to be dropped.
     *
     * @param difficulty input difficulty value by the user through the previous activity.
     */
    protected void analyseDifficulty(int difficulty) {
        switch (difficulty) {
            case 1:
                seconds = 2280;
                numOfCellDrop = 36;
                break;
            case 2:
                seconds = 2160;
                numOfCellDrop = 36 + (int) (Math.random() * 5);
                break;
            case 3:
                seconds = 2040;
                numOfCellDrop = 41 + (int) (Math.random() * 4);
                break;
            case 4:
                seconds = 1920;
                numOfCellDrop = 45 + (int) (Math.random() * 3);
                break;
            case 5:
                seconds = 1800;
                numOfCellDrop = 48 + (int) (Math.random() * 3);
                break;
            default:
                seconds = 1800;
                numOfCellDrop = 40;
                break;
        }
    }
}