package com.ayush.playsoduko.playsoduko;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * This class represents the GridActivity which describes a "Generic" Sudoku interface used in this
 * app. Elements can be removed and modified to suit the purpose. This class is abstract because we
 * are never making GridActivities. The purpose of this activity is to avoid code duplication.
 *
 * @author ayushranjan
 * @since 11/04/17.
 */
public abstract class GridActivity extends Activity {
    // constants
    public static final int KEYBOARD_SIZE = 10;
    public static final int COUNT_DOWN_INTERVAL = 1000;
    public static final String DIFFICULTY_TAG = "difficulty";

    // instance variables
    protected Sudoku sudoku;
    protected Button[][] cells;
    protected int[][] buttonId;
    protected Button[] keyboard;
    protected TextView timerTextView;
    protected CountDownTimer countDownTimer;
    protected Button positiveButton;
    protected Button negativeButton;
    protected TextView ownNumLeft;
    protected TextView otherNumLeft;
    protected int currentX;
    protected int currentY;

    /**
     * Sets the activity with teh generic elements of a Sudoku grid.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku_grid_layout);

        initialiseIds();
        initialiseCells();
        initialiseKeyboard();
        initialiseElements();
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
     * Initialises the elements in the keyboard and sets the correct listeners
     */
    protected void initialiseKeyboard() {
        keyboard = new Button[KEYBOARD_SIZE];
        keyboard[0] = (Button) findViewById(R.id.zero);
        keyboard[1] = (Button) findViewById(R.id.one);
        keyboard[2] = (Button) findViewById(R.id.two);
        keyboard[3] = (Button) findViewById(R.id.three);
        keyboard[4] = (Button) findViewById(R.id.four);
        keyboard[5] = (Button) findViewById(R.id.five);
        keyboard[6] = (Button) findViewById(R.id.six);
        keyboard[7] = (Button) findViewById(R.id.seven);
        keyboard[8] = (Button) findViewById(R.id.eight);
        keyboard[9] = (Button) findViewById(R.id.nine);

        keyboard[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 0);
                cells[currentX][currentY].setText("");
            }
        });

        keyboard[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 1);
                cells[currentX][currentY].setText("1");
            }
        });

        keyboard[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 2);
                cells[currentX][currentY].setText("2");
            }
        });

        keyboard[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 3);
                cells[currentX][currentY].setText("3");
            }
        });

        keyboard[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 4);
                cells[currentX][currentY].setText("4");
            }
        });

        keyboard[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 5);
                cells[currentX][currentY].setText("5");
            }
        });

        keyboard[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 6);
                cells[currentX][currentY].setText("6");
            }
        });

        keyboard[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 7);
                cells[currentX][currentY].setText("7");
            }
        });

        keyboard[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 8);
                cells[currentX][currentY].setText("8");
            }
        });

        keyboard[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 9);
                cells[currentX][currentY].setText("9");
            }
        });
    }

    /**
     * Initialises an array of Ids which refer to the 2D grid of buttons
     */
    private void initialiseIds() {
        buttonId = new int[Sudoku.DIMENSION][Sudoku.DIMENSION];

        buttonId[0][0] = R.id.index00;
        buttonId[0][1] = R.id.index01;
        buttonId[0][2] = R.id.index02;
        buttonId[0][3] = R.id.index03;
        buttonId[0][4] = R.id.index04;
        buttonId[0][5] = R.id.index05;
        buttonId[0][6] = R.id.index06;
        buttonId[0][7] = R.id.index07;
        buttonId[0][8] = R.id.index08;

        buttonId[1][0] = R.id.index10;
        buttonId[1][1] = R.id.index11;
        buttonId[1][2] = R.id.index12;
        buttonId[1][3] = R.id.index13;
        buttonId[1][4] = R.id.index14;
        buttonId[1][5] = R.id.index15;
        buttonId[1][6] = R.id.index16;
        buttonId[1][7] = R.id.index17;
        buttonId[1][8] = R.id.index18;

        buttonId[2][0] = R.id.index20;
        buttonId[2][1] = R.id.index21;
        buttonId[2][2] = R.id.index22;
        buttonId[2][3] = R.id.index23;
        buttonId[2][4] = R.id.index24;
        buttonId[2][5] = R.id.index25;
        buttonId[2][6] = R.id.index26;
        buttonId[2][7] = R.id.index27;
        buttonId[2][8] = R.id.index28;

        buttonId[3][0] = R.id.index30;
        buttonId[3][1] = R.id.index31;
        buttonId[3][2] = R.id.index32;
        buttonId[3][3] = R.id.index33;
        buttonId[3][4] = R.id.index34;
        buttonId[3][5] = R.id.index35;
        buttonId[3][6] = R.id.index36;
        buttonId[3][7] = R.id.index37;
        buttonId[3][8] = R.id.index38;

        buttonId[4][0] = R.id.index40;
        buttonId[4][1] = R.id.index41;
        buttonId[4][2] = R.id.index42;
        buttonId[4][3] = R.id.index43;
        buttonId[4][4] = R.id.index44;
        buttonId[4][5] = R.id.index45;
        buttonId[4][6] = R.id.index46;
        buttonId[4][7] = R.id.index47;
        buttonId[4][8] = R.id.index48;

        buttonId[5][0] = R.id.index50;
        buttonId[5][1] = R.id.index51;
        buttonId[5][2] = R.id.index52;
        buttonId[5][3] = R.id.index53;
        buttonId[5][4] = R.id.index54;
        buttonId[5][5] = R.id.index55;
        buttonId[5][6] = R.id.index56;
        buttonId[5][7] = R.id.index57;
        buttonId[5][8] = R.id.index58;

        buttonId[6][0] = R.id.index60;
        buttonId[6][1] = R.id.index61;
        buttonId[6][2] = R.id.index62;
        buttonId[6][3] = R.id.index63;
        buttonId[6][4] = R.id.index64;
        buttonId[6][5] = R.id.index65;
        buttonId[6][6] = R.id.index66;
        buttonId[6][7] = R.id.index67;
        buttonId[6][8] = R.id.index68;

        buttonId[7][0] = R.id.index70;
        buttonId[7][1] = R.id.index71;
        buttonId[7][2] = R.id.index72;
        buttonId[7][3] = R.id.index73;
        buttonId[7][4] = R.id.index74;
        buttonId[7][5] = R.id.index75;
        buttonId[7][6] = R.id.index76;
        buttonId[7][7] = R.id.index77;
        buttonId[7][8] = R.id.index78;

        buttonId[8][0] = R.id.index80;
        buttonId[8][1] = R.id.index81;
        buttonId[8][2] = R.id.index82;
        buttonId[8][3] = R.id.index83;
        buttonId[8][4] = R.id.index84;
        buttonId[8][5] = R.id.index85;
        buttonId[8][6] = R.id.index86;
        buttonId[8][7] = R.id.index87;
        buttonId[8][8] = R.id.index88;

    }

    /**
     * Initialises all the cells in the sudoku board to correct on click listeners
     */
    private void initialiseCells() {
        cells = new Button[Sudoku.DIMENSION][Sudoku.DIMENSION];

        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                cells[i][j] = (Button) findViewById(buttonId[i][j]);

                final int i_x = i;
                final int j_y = j;

                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unselectPrevious();
                        currentX = i_x;
                        currentY = j_y;
                        selectCurrent();
                        updateKeyBoard();
                    }
                });
            }
        }
    }

    /**
     * updates the keyboard each time a cell is changed. Does animation to drop invalid keys.
     */
    protected void updateKeyBoard() {
        reappearButtons();
        dissapearButtons();
    }

    /**
     * makes the invalid buttons for teh current cell drop by animation
     */
    protected void dissapearButtons() {
        Integer[] invalidDigits = sudoku.buttonsNotAvailable(currentX, currentY);
        for (Integer invalidDigit : invalidDigits) {
            YoYo.with(Techniques.Hinge)
                    .duration(1000)
                    .repeat(1)
                    .playOn(keyboard[invalidDigit]);
        }
    }

    /**
     * makes the valid keys appear
     */
    protected void reappearButtons() {
        Integer[] validDigits = sudoku.buttonsAvailable(currentX, currentY);
        for (Integer validDigit : validDigits) {
            int indexOutOfFive = validDigit % 5;
            if (indexOutOfFive == 4 || indexOutOfFive == 0) {
                YoYo.with(Techniques.BounceInRight)
                        .duration(1000)
                        .repeat(1)
                        .playOn(keyboard[validDigit]);
            } else {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(1000)
                        .repeat(1)
                        .playOn(keyboard[validDigit]);
            }
        }
    }

    /**
     * Makes the current cell look "selected" by changing the background
     */
    protected void selectCurrent() {
        cells[currentX][currentY].setBackground(getDrawable(R.drawable.selected_cell));
    }

    /**
     * Unselects the previous cell by changing background
     */
    private void unselectPrevious() {
        cells[currentX][currentY].setBackground(getDrawable(R.drawable.unselected_cell));
    }

    /**
     * Initialises other UI elements.
     */
    private void initialiseElements() {
        ownNumLeft = (TextView) findViewById(R.id.own_num_left);
        otherNumLeft = (TextView) findViewById(R.id.other_num_left);
        timerTextView = (TextView) findViewById(R.id.timer);
        positiveButton = (Button) findViewById(R.id.positive);
        negativeButton = (Button) findViewById(R.id.negative);
        currentX = 0;
        currentY = 0;
    }

    /**
     * Resets the background to unselected cells without text.
     */
    protected void resetBackground() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                cells[x][y].setBackground(getDrawable(R.drawable.unselected_cell));
            }
        }
    }

    /**
     * Makes the entered numbers background grey in colour.
     */
    protected void makeFeedGrey() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                if (sudoku.getCell(x, y) != Sudoku.EMPTY_VALUE) {
                    cells[x][y].setBackground(getDrawable(R.drawable.filled_cell));
                }
            }
        }
    }

    /**
     * Fills the grid with the sudoku's current state
     */
    protected void fillGrid() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                cells[x][y].setText(sudoku.getCellString(x, y));
            }
        }
    }

    // abstract methods

    /**
     * Displays a Dialog which declares the user as the winner.
     */
    protected abstract void showWinDialog();

    /**
     * Displays a Dialog which declares the user as the loser.
     */
    protected abstract void showLoseDialog();
}
