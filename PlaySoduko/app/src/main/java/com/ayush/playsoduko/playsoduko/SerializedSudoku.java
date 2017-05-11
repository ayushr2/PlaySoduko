package com.ayush.playsoduko.playsoduko;

import java.util.ArrayList;

/**
 * This object represents a Sudoku object compressed in such a way that it can be pushed into the
 * Firebase DB. Firebase does not acceot arrays and hence I had to convert int 2D array to
 * ArrayList<ArrayList<Integer>>. Both Sudoku and SerializedSudoku classes include constructors which
 * accepts the other object and converts it. Thus it is easy to inter convert between them
 *
 * @author ayushranjan
 * @see Sudoku
 * @since 29/04/17.
 */

public class SerializedSudoku {
    // member variables
    private ArrayList<ArrayList<Integer>> sudoku;


    /**
     * Default empty constructor. This is needed by the Firebase DB to push objects of this kind.
     */
    public SerializedSudoku() {
        // do nothing
    }

    /**
     * Constructor which takes in a Sudoku object and converts it into a SerializedSudoku object.
     *
     * @param other the Sudoku object to be converted
     */
    public SerializedSudoku(Sudoku other) {
        sudoku = new ArrayList<>();
        int[][] grid = other.getGrid();

        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            ArrayList<Integer> currentRow = new ArrayList<>();

            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                currentRow.add(grid[i][j]);
            }

            sudoku.add(currentRow);
        }
    }

    /**
     * Overloaded the equals method of the Object class. Compares two SerializedSudoku objects.
     * If there is a discrepancy in the structure or value in the objects it returns false
     *
     * @param other other SerializedSudoku object to compare this with
     * @return true if the objects are completely same
     */
    @Override
    public boolean equals(Object other) {
        SerializedSudoku otherSerialized = (SerializedSudoku) other;

        if (otherSerialized.getSudoku().size() != sudoku.size() ||
                otherSerialized.getSudoku().get(0).size() != sudoku.get(0).size()) {
            return false;
        }

        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                if (!otherSerialized.getSudoku().get(i).get(j).equals(sudoku.get(i).get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    // getter
    public ArrayList<ArrayList<Integer>> getSudoku() {
        return sudoku;
    }
}
