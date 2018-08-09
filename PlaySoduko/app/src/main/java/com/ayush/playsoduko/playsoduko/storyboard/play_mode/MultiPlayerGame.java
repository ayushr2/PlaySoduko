package com.ayush.playsoduko.playsoduko.storyboard.play_mode;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ayush.playsoduko.playsoduko.R;
import com.ayush.playsoduko.playsoduko.firebase_objects.Player;
import com.ayush.playsoduko.playsoduko.firebase_objects.SerializedSudoku;
import com.ayush.playsoduko.playsoduko.storyboard.HomeActivity;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;
import com.ayush.playsoduko.playsoduko.utilities.SudokuBoard;
import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity represents the interface where the user can play online against another opponent.
 * This activity extends SinglePlayerGame which in turn extends SudokuBoard which describes a
 * "Generic" Sudoku interface used in this app. I have removed and modified elements from it so it
 * suits the purpose.
 *
 * @author ayushranjan
 * @see SudokuBoard
 * @see SinglePlayerGame
 * @since 25/04/17.
 */
public class MultiPlayerGame extends SinglePlayerGame {

    public static final String PROGRESS_DIALOG_TITLE = "Finding Opponent";
    public static final String PROGRESS_DIALOG_MESSAGE = "Please wait...";
    public static final String OPPONENT_ID = "opponentId";
    public static final String SUDOKU_GRID = "grid";
    public static final String CELLS_LEFT = "cellsLeft";

    // UI elements
    private TextView ownNumLeft;
    private TextView otherNumLeft;

    // Firebase refs
    private DatabaseReference availableUsersReference;
    private DatabaseReference myReference;
    private DatabaseReference opponentReference;
    private DatabaseReference playersReference;

    // dialogs
    private ProgressDialog waitingForOpponentDialog;
    private AlertDialog.Builder quitDialog;
    private AlertDialog.Builder opponentQuitDialog;

    // instance variables
    private boolean inSession = false;
    private String opponentName;
    private Player myself;

    // listeners
    // before game starts
    private ValueEventListener waitingForOpponentListener;
    private ValueEventListener readSudokuBoardListener;

    // after game starts
    private ValueEventListener opponentNumCellsLeftChangeListener;
    private ValueEventListener opponentQuitListener;

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
        initialiseComponents();
        checkForAvailablePlayers();
    }

    /**
     * Calls the hierarchy of initialise methods and sets ups all fields correctly
     */
    private void initialiseComponents() {
        ownNumLeft = findViewById(R.id.own_num_left);
        otherNumLeft = findViewById(R.id.other_num_left);
        Profile myProfile = Profile.getCurrentProfile();
        myself = new Player(myProfile.getId(), myProfile.getFirstName(), 81);

        initialiseDialogs();
        initialiseDBComponents();
        initialiseListeners();
    }

    @Override
    protected void setUpButtons() {
        positiveButton.setVisibility(View.INVISIBLE);

        negativeButton.setText("Quit");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitDialog.show();
            }
        });
    }

    @Override
    protected void onKeyPressed() {
        if (!inSession)
            return;

        super.onKeyPressed();
        updateMyNumLeft();
    }

    @Override
    protected void startNewGame() {
        // do nothing here since opponent has to be matched asynchronously
    }

    @Override
    protected void startGame() {
        super.startGame();
        inSession = true;

        // get opponents name
        opponentReference.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                opponentName = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // set my cells left
        myself.setCellsLeft(sudoku.getNumLeft());
        updateMyNumLeft();

        // set listeners which are active till the game gets over
        opponentReference.child(CELLS_LEFT).addValueEventListener(opponentNumCellsLeftChangeListener);
        myReference.child(OPPONENT_ID).addValueEventListener(opponentQuitListener);
    }

    @Override
    protected void showLoseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.lost_message_online));

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void showWinDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.won_message));

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void startTimer() {
        // do not start timer
    }

    private void checkForAvailablePlayers() {
        availableUsersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (dsp.getValue(Boolean.class) != null) {
                        opponentFoundHandler(dsp.getKey());
                        break;
                    }
                }

                makeMatch();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void opponentFoundHandler(String opponentId) {
        myself.setOpponentId(opponentId);
        myReference.child(OPPONENT_ID).setValue(myself.getOpponentId());
        opponentReference = playersReference.child(myself.getOpponentId());

    }

    private void makeMatch() {
        if (myself.getOpponentId() == null) {
            // did not find an opponent, start waiting
            availableUsersReference.child(myself.getId()).setValue(true);
            waitingForOpponentDialog.show();
            myReference.child(OPPONENT_ID).addValueEventListener(waitingForOpponentListener);
        } else {
            sudoku = new Sudoku(cellsToDrop);
            opponentReference.child(OPPONENT_ID).setValue(myself.getId());
            myReference.child(SUDOKU_GRID).setValue(new SerializedSudoku(sudoku));
            startGame();
        }
    }

    private void updateMyNumLeft() {
        myReference.child(CELLS_LEFT).setValue(sudoku.getNumLeft());
        ownNumLeft.setText("You : " + sudoku.getNumLeft());

        if (sudoku.getNumLeft() == 0)
            showWinDialog();
    }

    private void initialiseDialogs() {
        // this dialog is triggered while waiting for opponent
        waitingForOpponentDialog = new ProgressDialog(this);
        waitingForOpponentDialog.setTitle(PROGRESS_DIALOG_TITLE);
        waitingForOpponentDialog.setMessage(PROGRESS_DIALOG_MESSAGE);
        waitingForOpponentDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                });
        waitingForOpponentDialog.setCancelable(false);

        // this dialog is triggered when quit is pressed
        quitDialog = new AlertDialog.Builder(MultiPlayerGame.this);
        quitDialog.setMessage(getString(R.string.quit_prompt));
        quitDialog.setPositiveButton(HomeActivity.POSITIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setNegativeButton(HomeActivity.NEGATIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // this dialog is shown when the opponent quit on their in phone
        opponentQuitDialog = new AlertDialog.Builder(this);
        opponentQuitDialog.setMessage(getString(R.string.opponent_quit_message));
        opponentQuitDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        opponentQuitDialog.setCancelable(false);
    }

    private void cleanUp() {
        availableUsersReference.child(myself.getId()).setValue(null);

        if (inSession) {
            opponentReference.child(OPPONENT_ID).setValue(null);
            cleanInGameMode();
            inSession = false;
        } else
            cleanInWaitMode();
    }

    private void cleanInGameMode() {
        opponentReference.child(CELLS_LEFT).removeEventListener(opponentNumCellsLeftChangeListener);
        myReference.child(OPPONENT_ID).removeEventListener(opponentQuitListener);
    }

    private void initialiseListeners() {
        // listeners before the game starts
        waitingForOpponentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String opponentId = dataSnapshot.getValue(String.class);
                if (opponentId != null) {
                    opponentFoundHandler(opponentId);
                    cleanInWaitMode();
                    opponentReference.child(SUDOKU_GRID).addValueEventListener(readSudokuBoardListener);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        readSudokuBoardListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SerializedSudoku serializedSudoku = dataSnapshot.getValue(SerializedSudoku.class);
                if (serializedSudoku != null) {
                    opponentReference.child(SUDOKU_GRID).removeEventListener(readSudokuBoardListener);
                    sudoku = new Sudoku(serializedSudoku);
                    waitingForOpponentDialog.cancel();
                    startGame();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        opponentNumCellsLeftChangeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(Integer.class) != null) {
                    otherNumLeft.setText(opponentName + " : " + dataSnapshot.getValue(Integer.class));
                    if (0 == dataSnapshot.getValue(Integer.class))
                        showLoseDialog();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        opponentQuitListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class) == null) {
                    opponentQuitDialog.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    private void cleanInWaitMode() {
        availableUsersReference.child(myself.getId()).setValue(null);
        myReference.child(OPPONENT_ID).removeEventListener(waitingForOpponentListener);
    }

    private void initialiseDBComponents() {
        DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        availableUsersReference = rootReference.child("available");
        playersReference = rootReference.child("players");
        myReference = playersReference.child(Profile.getCurrentProfile().getId());
        myReference.setValue(myself);
    }

    /**
     * When the user presses the back button he/she quits the game and the opponent automatically wins.
     */
    @Override
    public void onBackPressed() {
        quitDialog.show();
    }


    @Override
    protected void onDestroy() {
        cleanUp();
        super.onDestroy();
    }
}