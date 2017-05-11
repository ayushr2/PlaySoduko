package com.ayush.playsoduko.playsoduko;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This activity represents the interface where the user can enter numbers into an empty grid. This
 * activity extends GridActivity which describes a "Generic" Sudoku interface used in this app. I have
 * removed and modified elements from it so it suits the purpose.
 *
 * @see GridActivity
 * @author ayushranjan
 * @since 13/04/17.
 */
public class SolveActivity extends GridActivity {

    /**
     * This method initialises all UI elements and sets the correct button listeners. Makes all the
     * top bar elements invisible because it is not needed.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownNumLeft.setVisibility(View.INVISIBLE);
        otherNumLeft.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        positiveButton.setText(R.string.go);
        negativeButton.setText(R.string.clear);

        // new empty sudoku
        sudoku = new Sudoku();
        selectCurrent();

        // button listeners
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFeedGrey();
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

    @Override
    protected void showWinDialog() {
        // do nothing
    }

    @Override
    protected void showLoseDialog() {
        // do nothing
    }
}
