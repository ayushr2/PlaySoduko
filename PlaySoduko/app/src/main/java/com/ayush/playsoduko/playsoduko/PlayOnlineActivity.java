package com.ayush.playsoduko.playsoduko;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity represents the interface where the user can play online against another opponent.
 * This activity extends PlayLocalActivity which in turn extends GridActivity which describes a
 * "Generic" Sudoku interface used in this app. I have removed and modified elements from it so it
 * suits the purpose.
 *
 * @author ayushranjan
 * @see GridActivity
 * @see PlayLocalActivity
 * @since 25/04/17.
 */
public class PlayOnlineActivity extends PlayLocalActivity {

    public static final String PROGRESS_DIALOG_TITLE = "Finding Opponent";
    public static final String PROGRESS_DIALOG_MESSAGE = "Please wait...";
    private Player myself;
    private Player opponent;
    private String opponentId;
    private DatabaseReference availableUsersReference;
    private DatabaseReference playersReference;
    ValueEventListener waitingForOpponentListener;
    ValueEventListener updateOpponentListener;
    ValueEventListener opponentChangeListener;
    ProgressDialog waitingForOpponentDialog;

    /**
     * Makes the appropriate text views appear and uses the firebase database to hook the player with
     * a random online player. The player can keep track of the opponent's number of cells left to win.
     * The user who finishes the game first will win. Both the players are provided with the same Sudoku
     * game.
     *
     * @param savedInstanceState important bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        waitingForOpponentDialog = new ProgressDialog(this);
        waitingForOpponentDialog.setTitle(PROGRESS_DIALOG_TITLE);
        waitingForOpponentDialog.setMessage(PROGRESS_DIALOG_MESSAGE);
        waitingForOpponentDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        availableUsersReference.child(myself.getId()).setValue(null);
                        dialog.cancel();
                        playersReference.child(myself.getId()).removeEventListener(waitingForOpponentListener);
                        startActivity(new Intent(getApplication(), PlayActivity.class));
                    }
                });

        otherNumLeft.setVisibility(View.VISIBLE);
        ownNumLeft.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        positiveButton.setText("Home");
    }

    /**
     * Displays a Dialog which declares the user as the loser.
     */
    @Override
    protected void showLoseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.lost_message_online));

        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBackground();
                initSudokuBoard();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void setButtonListeners() {
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(PlayOnlineActivity.this);
                builder.setMessage(getString(R.string.quit_prompt));

                builder.setPositiveButton(HomeActivity.POSITIVE_TAG, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playersReference.child(myself.getId()).child("grid").setValue(null);
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                });

                builder.setNegativeButton(HomeActivity.NEGATIVE_TAG, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(PlayOnlineActivity.this);
                builder.setMessage(getString(R.string.quit_prompt));

                builder.setPositiveButton(HomeActivity.POSITIVE_TAG, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playersReference.child(myself.getId()).child("grid").setValue(null);
                        startActivity(new Intent(getApplicationContext(), PlayActivity.class));
                    }
                });

                builder.setNegativeButton(HomeActivity.NEGATIVE_TAG, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
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
                updateMyNumLeft();
            }
        });

        keyboard[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 1);
                cells[currentX][currentY].setText("1");
                updateMyNumLeft();
            }
        });

        keyboard[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 2);
                cells[currentX][currentY].setText("2");
                updateMyNumLeft();
            }
        });

        keyboard[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 3);
                cells[currentX][currentY].setText("3");
                updateMyNumLeft();
            }
        });

        keyboard[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 4);
                cells[currentX][currentY].setText("4");
                updateMyNumLeft();
            }
        });

        keyboard[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 5);
                cells[currentX][currentY].setText("5");
                updateMyNumLeft();
            }
        });

        keyboard[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 6);
                cells[currentX][currentY].setText("6");
                updateMyNumLeft();
            }
        });

        keyboard[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 7);
                cells[currentX][currentY].setText("7");
                updateMyNumLeft();
            }
        });

        keyboard[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 8);
                cells[currentX][currentY].setText("8");
                updateMyNumLeft();
            }
        });

        keyboard[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setSudoku(currentX, currentY, 9);
                cells[currentX][currentY].setText("9");
                updateMyNumLeft();
            }
        });
    }

    private void updateMyNumLeft() {
        playersReference.child(myself.getId()).child("cellsLeft").setValue(sudoku.getNumLeft());
        ownNumLeft.setText("You : " + sudoku.getNumLeft());
        if (sudoku.getNumLeft() == 0) {
            showWinDialog();
            ownNumLeft.setText("You : " + sudoku.getNumLeft());
        }
    }

    /**
     * Initialises sudoku board and finds an opponent who is online.
     */
    @Override
    protected void initSudokuBoard() {
        sudoku = new Sudoku(numOfCellDrop);
        findOpponent();
    }

    /**
     * This method holds other methods which are called after an opponent is found.
     */
    private void postFoundOpponent() {
        setGameListeners();
        fillGrid();
        immutateFeed();
        setCurrentXY();
        updateKeyBoard();
        ownNumLeft.setText("You : " + sudoku.getNumLeft());
    }

    /**
     * Finds an opponent for the user using Firebase DB.
     */
    private void findOpponent() {
        initDBComponents();
        playersReference.child(myself.getId()).setValue(myself);

        availableUsersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (dsp.getValue(Boolean.class) && !dsp.getKey().equals(myself.getId())) {
                        opponentId = dsp.getKey();
                        break;
                    }
                }
                makeMatch(opponentId);
                availableUsersReference.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Makes a match based on the Facebook ID of the opponent. The opponent ID may be null of a vaild
     * ID. If the other is valid, then it pull the opponent object, puts this players sudoku into that
     * opponent, puts its own id as the opponent's opponentId and push it back to the database.
     * If the opponent ID is null, it means that there are no other opponents available at the moment.
     * Then it puts the user on hold. if another user comes oniine, that user would detect this one
     * and change its sudoku. Then we will detect it and move on to the game.
     *
     * @param opponentId String value of the opponent's ID.
     */
    private void makeMatch(final String opponentId) {
        if (opponentId == null) {
            availableUsersReference.child(myself.getId()).setValue(true);

            waitingForOpponentDialog.show();
            waitingForOpponentListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SerializedSudoku change = dataSnapshot.child("grid")
                            .getValue(SerializedSudoku.class);

                    if (!change.equals(myself.getGrid())) {
                        myself = dataSnapshot.getValue(Player.class);
                        sudoku = new Sudoku(myself.getGrid());
                        availableUsersReference.child(myself.getId()).setValue(null);
                        waitingForOpponentDialog.cancel();
                        playersReference.child(myself.getId()).setValue(myself);
                        playersReference.child(myself.getId()).removeEventListener(waitingForOpponentListener);
                        postFoundOpponent();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            playersReference.child(myself.getId()).addValueEventListener(waitingForOpponentListener);

        } else {
            availableUsersReference.child(opponentId).setValue(null);
            availableUsersReference.child(myself.getId()).setValue(null);
            myself.setOpponentId(opponentId);
            playersReference.child(myself.getId()).setValue(myself);

            updateOpponentListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    opponent = dataSnapshot.getValue(Player.class);
                    opponent.setGrid(myself.getGrid());
                    opponent.setCellsLeft(myself.getCellsLeft());
                    opponent.setOpponentId(myself.getId());
                    playersReference.child(opponentId).setValue(opponent);
                    playersReference.child(opponentId).removeEventListener(updateOpponentListener);
                    postFoundOpponent();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            playersReference.child(opponentId).addListenerForSingleValueEvent(updateOpponentListener);
        }
    }

    /**
     * Sets a listener to the opponent's node on the DB. When it updates its cellsLeft, the listener
     * is triggered and hence this player updates its opponent's cells left text view.
     */
    private void setGameListeners() {
        opponentChangeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player opp = dataSnapshot.getValue(Player.class);
                if (opp.getGrid() == null) {
                    showQuitDialog();
                }
                otherNumLeft.setText(opp.getName() + " : " + opp.getCellsLeft());
                if (opp.getCellsLeft() == 0) {
                    showLoseDialog();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        playersReference.child(myself.getOpponentId()).addValueEventListener(opponentChangeListener);
    }

    /**
     * Displays that the player won because the other player quit or started a new game. This is triggered
     * when the other player leaves the game.
     */
    private void showQuitDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.quit_message));

        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBackground();
                initSudokuBoard();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * Initialises both player objects and sets the reference objects.
     */
    private void initDBComponents() {
        opponent = null;
        opponentId = null;
        myself = new Player(Profile.getCurrentProfile().getId(),
                Profile.getCurrentProfile().getFirstName(), sudoku);
        availableUsersReference = FirebaseDatabase.getInstance().getReference().child("available");
        playersReference = FirebaseDatabase.getInstance().getReference().child("players");
    }

    /**
     * When the user presses the back button he/she quits the game and the opponent automatically wins.
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlayOnlineActivity.this);
        builder.setMessage(getString(R.string.quit_prompt));

        builder.setPositiveButton(HomeActivity.POSITIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playersReference.child(myself.getId()).child("grid").setValue(null);
                startActivity(new Intent(getApplicationContext(), PlayActivity.class));
            }
        });

        builder.setNegativeButton(HomeActivity.NEGATIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}