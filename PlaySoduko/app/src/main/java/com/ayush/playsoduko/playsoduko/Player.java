package com.ayush.playsoduko.playsoduko;

/**
 * This class represents a Player object which holds information about the Player. Holds the name,
 * the facebook ID and the current number of cells left to complete the sudoku. We use the Facebook
 * ID to to identify unique players. The facebook ID becomes the "key" in the firebase database and
 * if the opponent has access to this players facebook ID, they will have access to the whole object
 * and know their name and the number of cells left.
 *
 * @author ayushranjan
 * @since 17/04/17.
 */
public class Player {

    // member variables
    private String opponentId;
    private String name;
    private int cellsLeft = 81;
    private String id;
    private SerializedSudoku grid;

    /**
     * Default Constructor. Doesn't do anything.
     */
    public Player() {
        // Do nothing
    }

    /**
     * Constructor which initialises the user ID and name.
     *
     * @param userId       facebook unique ID
     * @param facebookName facebook name
     * @param game         the initial Sudoku game the Player object is initiated with
     */
    public Player(String userId, String facebookName, Sudoku game) {
        id = userId;
        name = facebookName;
        grid = new SerializedSudoku(game);
        cellsLeft = game.getNumLeft();
    }

    // setter
    public void setCellsLeft(int cellsLeft) {
        this.cellsLeft = cellsLeft;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getCellsLeft() {
        return cellsLeft;
    }

    public String getId() {
        return id;
    }

    public SerializedSudoku getGrid() {
        return grid;
    }

    public void setGrid(SerializedSudoku grid) {
        this.grid = grid;
    }
    public String getOpponentId() {
        return opponentId;
    }
}
