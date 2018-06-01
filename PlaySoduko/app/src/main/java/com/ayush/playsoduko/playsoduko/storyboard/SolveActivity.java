package com.ayush.playsoduko.playsoduko.storyboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ayush.playsoduko.playsoduko.utilities.SudokuBoard;
import com.ayush.playsoduko.playsoduko.R;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;

/**
 * This activity represents the interface where the user can enter numbers into an empty grid. This
 * activity extends SudokuBoard which describes a "Generic" Sudoku interface used in this app. I have
 * removed and modified elements from it so it suits the purpose.
 *
 * @see SudokuBoard
 * @author ayushranjan
 * @since 13/04/17.
 */
public class SolveActivity extends SudokuBoard {

    /**
     * This method initialises all UI elements and sets the correct button listeners. Makes all the
     * top bar elements invisible because it is not needed.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // new empty sudoku
        sudoku = new Sudoku();
        setUpButtons();
    }

    /**
     * Sets up the button names and on click listeners to perform the required actions
     */
    private void setUpButtons() {
        positiveButton.setText(R.string.go);
        negativeButton.setText(R.string.clear);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freezeGrid();
                sudoku.solve();
                if (sudoku.isSolved()) {
                    fillGrid();
                } else {
                    resetBackground();
                    Toast.makeText(getApplicationContext(), getString(R.string.no_solution),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku = new Sudoku();
                resetBackground();
                fillGrid();
            }
        });
    }

    // overridden methods

    @Override
    protected void showWinDialog() {
        // do nothing
    }

    @Override
    protected void showLoseDialog() {
        // do nothing
    }
}
