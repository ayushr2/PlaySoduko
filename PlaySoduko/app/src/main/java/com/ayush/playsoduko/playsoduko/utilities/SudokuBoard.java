package com.ayush.playsoduko.playsoduko.utilities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ayush.playsoduko.playsoduko.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Arrays;
import java.util.HashSet;

/**
 * This class represents the SudokuBoard which describes a "Generic" Sudoku interface used in this
 * app. Elements can be removed and modified to suit the purpose. This class is abstract because we
 * are never making GridActivities. The purpose of this activity is to avoid code duplication.
 *
 * @author ayushranjan
 * @since 11/04/17.
 */
public abstract class SudokuBoard extends Activity {
    // constants
    public static final int KEYBOARD_SIZE = 10;
    public static final int COUNT_DOWN_INTERVAL = 1000;
    public static final String DIFFICULTY_TAG = "difficulty";
    private static final String[] KEYBOARD_STRINGS = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    // instance variables
    protected Sudoku sudoku;
    protected Button[][] cells;
    protected int[][] buttonId;
    protected Button[] keyboard;
    protected int[] keyboardId;
    private boolean[] keyboardVisible;
    protected Button positiveButton;
    protected Button negativeButton;
    private int currentRow = 0;
    private int currentColumn = 0;

    /**
     * Sets the activity with teh generic elements of a Sudoku grid.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku_grid_layout);
        initialiseComponents();
    }

    /**
     * Initialises UI components and sets up on click listeners
     */
    private void initialiseComponents() {
        initialiseIds();
        initialiseCells();
        initialiseKeyboard();

        positiveButton = findViewById(R.id.positive);
        negativeButton = findViewById(R.id.negative);
    }

    /**
     * Initialises an array of Ids which refer to the 2D grid of buttons
     */
    private void initialiseIds() {
        // SUDOKU GRID IDs
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


        // KEYBOARD IDs
        keyboardId = new int[KEYBOARD_SIZE];

        keyboardId[0] = R.id.zero;
        keyboardId[1] = R.id.one;
        keyboardId[2] = R.id.two;
        keyboardId[3] = R.id.three;
        keyboardId[4] = R.id.four;
        keyboardId[5] = R.id.five;
        keyboardId[6] = R.id.six;
        keyboardId[7] = R.id.seven;
        keyboardId[8] = R.id.eight;
        keyboardId[9] = R.id.nine;
    }

    /**
     * Initialises the elements in the keyboard and sets the correct listeners
     */
    private void initialiseKeyboard() {
        keyboardVisible = new boolean[KEYBOARD_SIZE];
        Arrays.fill(keyboardVisible, true);

        keyboard = new Button[KEYBOARD_SIZE];

        for (int i = 0; i < KEYBOARD_SIZE; i++) {
            keyboard[i] = findViewById(keyboardId[i]);
            final int finalI = i;

            keyboard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sudoku.setValue(currentRow, currentColumn, finalI);
                    cells[currentRow][currentColumn].setText(KEYBOARD_STRINGS[finalI]);
                    onKeyPressed();
                }
            });
        }
    }

    /**
     * Initialises all the cells in the sudoku board to correct on click listeners
     */
    private void initialiseCells() {
        cells = new Button[Sudoku.DIMENSION][Sudoku.DIMENSION];

        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                cells[i][j] = findViewById(buttonId[i][j]);

                final int i_x = i;
                final int j_y = j;

                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCurrentPosition(i_x, j_y);
                    }
                });
            }
        }
    }

    /**
     * This method is the only interface exposed to child classes using which they can change
     * position of the currently selected cell on the grid.
     * This method deselects the previous cell, selects the new one and updates keyboard using
     * animation.
     *
     * @param newX row index of the to be selected cell
     * @param newY column index of the to be selected cell
     */
    protected void setCurrentPosition(int newX, int newY) {
        cells[currentRow][currentColumn].setBackground(getDrawable(R.drawable.unselected_cell));
        currentRow = newX;
        currentColumn = newY;
        cells[currentRow][currentColumn].setBackground(getDrawable(R.drawable.selected_cell));
        updateKeyBoard();
    }

    /**
     * Updates the keyboard each time a cell is changed. Does animation to cleanly show visible and
     * invisible cells
     */
    private void updateKeyBoard() {
        HashSet<Integer> toRemove = new HashSet<>(Arrays.asList(sudoku.buttonsNotAvailable(currentRow, currentColumn)));
        for (int i = 0; i < KEYBOARD_SIZE; i++) {
            if (keyboardVisible[i] && toRemove.contains(i)) {
                YoYo.with(Techniques.FadeOut)
                        .duration(500)
                        .repeat(1)
                        .playOn(keyboard[i]);
                keyboardVisible[i] = false;
                keyboard[i].setClickable(false);
            }

            if (!keyboardVisible[i] && !toRemove.contains(i)) {
                YoYo.with(Techniques.FadeIn)
                        .duration(500)
                        .repeat(1)
                        .playOn(keyboard[i]);
                keyboardVisible[i] = true;
                keyboard[i].setClickable(true);
            }
        }
    }

    /**
     * Resets the background to unselected cells without text. Also makes all cells "selectable".
     */
    protected void resetBackground() {
        unfreezeGrid();
        clearGridContent();
    }

    protected void clearGridContent() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                cells[x][y].setText(KEYBOARD_STRINGS[0]);
            }
        }
    }

    /**
     * Unfreezes the grid and hence makes all cells clickable and unselected
     */
    protected void unfreezeGrid() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                cells[x][y].setBackground(getDrawable(R.drawable.unselected_cell));
                cells[x][y].setClickable(true);
            }
        }
    }

    /**
     * Sets the board with one cell selected and the keyboard updated for that cell.
     */
    protected void setStartPosition() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                if (sudoku.getValue(x, y) == Sudoku.EMPTY_VALUE) {
                    setCurrentPosition(x, y);
                    return;
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
                cells[x][y].setText(sudoku.toString(x, y));
            }
        }
    }

    /**
     * Makes all the cells which are already filled "unselectable". Also gives them a grey background
     * so its easier for users to infer.
     */
    protected void freezeGrid() {
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                if (sudoku.getValue(x, y) != Sudoku.EMPTY_VALUE) {
                    cells[x][y].setBackground(getDrawable(R.drawable.filled_cell));
                    cells[x][y].setClickable(false);
                }
            }
        }
    }

    /**
     * Callback function to handle an event of a button being pressed
     */
    protected void onKeyPressed() {
        updateKeyBoard();
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
