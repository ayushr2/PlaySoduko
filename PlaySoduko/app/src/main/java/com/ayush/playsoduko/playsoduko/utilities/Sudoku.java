package com.ayush.playsoduko.playsoduko.utilities;

import com.ayush.playsoduko.playsoduko.firebase_objects.SerializedSudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class holds function which are used to manipulate sudoku puzzles. It holds functions to check
 * if a given sudoku puzzle is solvable, it solves a sudoku. It also generates Sudokus.
 *
 * @author ayushranjan
 * @since 02/11/16.
 */
public class Sudoku {

    // constants
    public static final int DIMENSION = 9;
    public static final int EMPTY_VALUE = 0;
    public static final int TOTAL_CELLS = 81;

    // private instance variables
    private int[][] grid;
    private boolean solved;
    private int numLeft;

    // constructors

    public Sudoku(SerializedSudoku sudoku) {
        init();
        for (int i = 0; i < Sudoku.DIMENSION; i++) {
            for (int j = 0; j < Sudoku.DIMENSION; j++) {
                grid[i][j] = sudoku.getSudoku().get(i).get(j);
                if (grid[i][j] != 0) {
                    numLeft--;
                }
            }
        }

        solved = (numLeft == 0);
    }

    /**
     * Default constructor. Sets the grid to empty.
     */
    public Sudoku() {
        init();
    }

    /**
     * Copy Constructor. Copies another sudoku completely into itself.
     *
     * @param other the other Sudoku object to be copied from
     */
    public Sudoku(Sudoku other) {
        this.grid = new int[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            System.arraycopy(other.grid[i], 0, this.grid[i], 0, DIMENSION);
        }
        this.solved = other.solved;
        this.numLeft = other.numLeft;
    }

    /**
     * Constructor which constructs a sudoku with drop number of empty cells
     *
     * @param drop the number of cells to be dropped
     */
    public Sudoku(int drop) {
        init();
        this.random();
        this.numLeft = drop;
        dropCells(drop);
    }

    // Public member functions

    public void setSudoku(int x, int y, int value) {
        int initialValue = grid[x][y];
        grid[x][y] = value;
        if (initialValue == 0) {
            if (value != 0) {
                numLeft--;
            }
        } else {
            if (value == 0) {
                numLeft++;
            }
        }
        if (numLeft == 0) {
            solved = true;
        }
    }

    /**
     * Returns an int[] which holds the values that CANNOT be assigned in the current cell based on
     * the rules of Sudoku puzzles.
     *
     * @param x current cell x position
     * @param y current cell x position
     * @return int[] of values which are invalid in current cell
     */
    public Integer[] buttonsNotAvailable(int x, int y) {
        ArrayList<Integer> numbersOccurred = new ArrayList<>();
        traverseRowColumn(x, y, numbersOccurred);
        traverseBox(x, y, numbersOccurred);
        numbersOccurred.remove(Integer.valueOf(EMPTY_VALUE));
        return numbersOccurred.toArray(new Integer[numbersOccurred.size()]);
    }

    /**
     * Returns an int[] which holds the values that CANNOT be assigned in the current cell based on
     * the rules of Sudoku puzzles.
     *
     * @param x current cell x position
     * @param y current cell x position
     * @return int[] of values which are invalid in current cell
     */
    public Integer[] buttonsAvailable(int x, int y) {
        ArrayList<Integer> inverse = new ArrayList<>(Arrays.asList(buttonsNotAvailable(x, y)));
        ArrayList<Integer> result = new ArrayList<>();

        for (int currentNumber = 1; currentNumber <= DIMENSION; currentNumber++) {
            if (!inverse.contains(currentNumber)) {
                result.add(currentNumber);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    /**
     * This functions solves the current Sudoku object. It only solves it IF it has a unique solution.
     * Otherwise the sudoku remains unchanged. I have done this so that we know if a generated sudoku
     * has a unique solution by testing it using solvable method. Only if it has a unique solution
     * will it be solvable.
     */
    public void solve() {
        Sudoku copy = new Sudoku(this);
        int numberOfZeros = numLeft;
        int traversals = 0;

        while (numberOfZeros > 0) {
            for (int x = 0; x < DIMENSION; x++) {
                currentIteration:
                for (int y = 0; y < DIMENSION; y++) {

                    if (copy.grid[x][y] != 0) {
                        continue;
                    }

                    Integer[] possibilities = copy.buttonsAvailable(x, y);

                    if (possibilities.length == 1) {
                        copy.grid[x][y] = possibilities[0];
                        numberOfZeros--;
                        continue;
                    }

                    Set<Integer> columnPossibilities = copy.getColumnPossibilities(x, y);
                    Set<Integer> rowPossibilities = copy.getRowPossibilities(x, y);
                    Set<Integer> boxPossibilities = copy.getBoxPossibilities(x, y);

                    for (Integer current : possibilities) {
                        if (!columnPossibilities.contains(current) ||
                                !rowPossibilities.contains(current) ||
                                !boxPossibilities.contains(current)) {
                            copy.grid[x][y] = current;
                            numberOfZeros--;
                            if (!columnPossibilities.contains(current)) {
                            } else {
                                if (!rowPossibilities.contains(current)) {
                                } else {
                                }
                            }
                            continue currentIteration;
                        }
                    }
                }
            }
            traversals++;

            if (numberOfZeros == 0) {
                solved = true;
                numLeft = numberOfZeros;
                this.grid = copy.grid;
                return;
            }

            if (traversals > numLeft) {
                return;
            }
        }
    }

    /**
     * Completely randomises the current sudoku and replaces the current content with a random solved
     * sudoku.
     */
    public void random() {
        main:
        while (true) {
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    Integer[] available = buttonsAvailable(i, j);
                    if (available.length == 0) {
                        init();
                        continue main;
                    }
                    int index = (int) (Math.random() * available.length);
                    grid[i][j] = available[index];
                }
            }

            return;
        }
    }

    /**
     * Returns true if the current sudoku is solvable and has a unique solution. I it has a unique
     * solution then it can be solved by a human mind and involves no guess work. There is a definite
     * solution which can be solved.
     *
     * @return the solvable characteristic of this sudoku
     */
    public boolean solvable() {
        Sudoku copy = new Sudoku(this);
        copy.solve();
        return copy.solved;
    }

    /**
     * Overrides the default toString method Prints the sudoku into a 2D array and returns it as a string. This is used for solely for
     * debugging purposes
     *
     * @return the string representation of a sudoku
     */
    @Override
    public String toString() {
        return Arrays.deepToString(this.grid).replace("], ", "]\n");
    }

    // Private Member functions

    /**
     * Helper function resets the grid to empty, solved to false and number of 0s to 81
     */
    private void init() {
        grid = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solved = false;
        numLeft = TOTAL_CELLS;
    }

    /**
     * Helper function which randomly and uniformly drops not empty and returns only when the newly
     * generated puzzle has a unique solution (solvable by humans)
     *
     * @param drop the number of cells to drop
     */
    public void dropCells(int drop) {
        numLeft = drop;
        Sudoku copy;
        while (true) {
            copy = new Sudoku(this);
            int x = 0;

            for (int dropped = 0; dropped < drop; dropped++) {
                x %= DIMENSION;
                int y = (int) (Math.random() * DIMENSION);
                if (copy.grid[x][y] == EMPTY_VALUE) {
                    dropped--;
                } else {
                    copy.grid[x][y] = 0;
                    x++;
                }
            }

            if (copy.solvable()) {
                this.grid = copy.grid;
                break;
            }
        }
    }

    /**
     * This function traverses through the box of the current cell's box and keeps adding to
     * numbersOccurred the numbers which have already occurred. It does not duplicate data in the
     * ArrayList.
     *
     * @param x               x coordinate of the cell
     * @param y               y coordinate of the cell
     * @param numbersOccurred ArrayList containing numbers already occurred
     */
    private void traverseBox(int x, int y, ArrayList<Integer> numbersOccurred) {
        int lowerX = getBoxLowerIndex(x);
        int upperX = getBoxUpperIndex(x);

        int lowerY = getBoxLowerIndex(y);
        int upperY = getBoxUpperIndex(y);
        int current;

        for (int xIndex = lowerX; xIndex <= upperX; xIndex++) {
            for (int yIndex = lowerY; yIndex <= upperY; yIndex++) {
                current = grid[xIndex][yIndex];
                if (!numbersOccurred.contains(current)) {
                    numbersOccurred.add(current);
                }
            }
        }
    }

    /**
     * Gets the upper index of the current box. The same function works for both x and y coordinate
     * axises because of symmetry.
     *
     * @param index index of current cell. in X or Y axis
     * @return the upper index of the current box
     */
    private int getBoxUpperIndex(int index) {
        int lower = getBoxLowerIndex(index);
        return lower + 2;
    }

    /**
     * Gets the lower index of the current box. The same function works for both x and y coordinate
     * axises because of symmetry.
     *
     * @param index index of current cell. in X or Y axis
     * @return the lower index of the current box
     */
    private int getBoxLowerIndex(int index) {
        int diff = index % 3;
        return index - diff;
    }

    /**
     * This function traverses through the current cell's row and column and keeps adding to
     * numbersOccurred the numbers which have already occurred. That is, all the values which cannot
     * be added in the current call. It does not duplicate data in the ArrayList.
     *
     * @param x               x coordinate of the cell
     * @param y               y coordinate of the cell
     * @param numbersOccurred ArrayList containing numbers already occurred
     */
    private void traverseRowColumn(int x, int y, ArrayList<Integer> numbersOccurred) {
        int current;
        // search column and row
        for (int i = 0; i < DIMENSION; i++) {
            // column
            current = grid[i][y];
            if (!numbersOccurred.contains(current)) {
                numbersOccurred.add(current);
            }

            // row
            current = grid[x][i];
            if (!numbersOccurred.contains(current)) {
                numbersOccurred.add(current);
            }
        }
    }

    /**
     * Returns a set of integers which holds ALL the numbers which the current box can hold (except
     * the current cell) So it parses through all the cells in the box which the current cell is in
     * and keeps appending unique numbers which other cells can possibly hold.
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return Set of numbers which has other cells around this cell can hold
     */
    private Set<Integer> getBoxPossibilities(int x, int y) {
        Set<Integer> possibilitiesOfBox = new HashSet<>();

        int lowerX = getBoxLowerIndex(x);
        int upperX = getBoxUpperIndex(x);

        int lowerY = getBoxLowerIndex(y);
        int upperY = getBoxUpperIndex(y);

        for (int xIndex = lowerX; xIndex <= upperX; xIndex++) {
            for (int yIndex = lowerY; yIndex <= upperY; yIndex++) {
                if (grid[xIndex][yIndex] == EMPTY_VALUE && (xIndex != x || yIndex != y)) {
                    possibilitiesOfBox.addAll(Arrays.asList(buttonsAvailable(xIndex, yIndex)));
                }
            }
        }

        return possibilitiesOfBox;
    }

    /**
     * Returns a set of integers which holds ALL the numbers which the current cell's row can hold
     * (except the current cell) So it parses through all the cells in the row which the current cell
     * is in and keeps appending unique numbers which other cells can possibly hold.
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return Set of numbers which has other cells in the current cell's row can hold
     */
    private Set<Integer> getRowPossibilities(int x, int y) {
        Set<Integer> possibilitiesOfRow = new HashSet<>();

        for (int column = 0; column < DIMENSION; column++) {
            if (grid[x][column] == EMPTY_VALUE && column != y) {
                possibilitiesOfRow.addAll(Arrays.asList(buttonsAvailable(x, column)));
            }
        }

        return possibilitiesOfRow;
    }

    /**
     * Returns a set of integers which holds ALL the numbers which the current cell's column can hold
     * (except the current cell) So it parses through all the cells in the column which the current
     * cell is in and keeps appending unique numbers which other cells can possibly hold.
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return Set of numbers which has other cells in the current cell's column can hold
     */
    private Set<Integer> getColumnPossibilities(int x, int y) {
        Set<Integer> possibilitiesOfRow = new HashSet<>();

        for (int row = 0; row < DIMENSION; row++) {
            if (grid[row][y] == EMPTY_VALUE && row != x) {
                possibilitiesOfRow.addAll(Arrays.asList(buttonsAvailable(row, y)));
            }
        }

        return possibilitiesOfRow;
    }

    // setters

    /**
     * Setter function which enables us to set values in the sudoku. One at a time
     *
     * @param x   x coordinate of grid
     * @param y   y coordinate of grid
     * @param val the value to update into the grid
     */
    public void updateSudoku(int x, int y, int val) {
        grid[x][y] = val;
    }

    // getters

    /**
     * Getter function. Gives the number of cells left to be solved
     *
     * @return numLeft variable
     */
    public int getNumLeft() {
        return numLeft;
    }

    /**
     * Getter function. Returns the grid itself
     *
     * @return numLeft variable
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Getter function. Tells if the sudoku is solved.
     *
     * @return numLeft variable
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Returns the string value of the value held in the cell indicated. This method is used to set
     * the text in the buttons which only takes in Sting input. If the cell holds 0 (EMPTY_VALUE),
     * returns an empty string.
     *
     * @param x x coord of the cell
     * @param y y coord of the cell
     * @return The string value of the value in that cell.
     */
    public String getCellString(int x, int y) {
        if (grid[x][y] == 0) {
            return "";
        } else {
            return String.valueOf(grid[x][y]);
        }
    }

    /**
     * Gets is the cell's int value
     *
     * @param x x coord of the cell
     * @param y y coord of the cell
     * @return int value held by that cell
     */
    public int getCell(int x, int y) {
        return grid[x][y];
    }
}