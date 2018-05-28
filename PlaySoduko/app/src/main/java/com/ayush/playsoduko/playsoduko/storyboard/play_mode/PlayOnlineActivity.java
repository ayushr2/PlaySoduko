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
import com.ayush.playsoduko.playsoduko.utilities.GridActivity;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;
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
    public static final String OPPONENT_ID = "opponentId";
    public static final String SUDOKU_GRID = "grid";
    public static final String CELLS_LEFT = "cellsLeft";

    private TextView ownNumLeft;
    private TextView otherNumLeft;
    private DatabaseReference availableUsersReference;
    private DatabaseReference myReference;
    private DatabaseReference rootReference;
    private ProgressDialog waitingForOpponentDialog;
    private AlertDialog.Builder quitDialog;
    private AlertDialog.Builder opponentQuitDialog;
    private DatabaseReference playersReference;
    private String opponentName;
    private Profile myProfile;
    private Player myself;
    private ValueEventListener waitingForOpponentListener;
    private ValueEventListener readSudokuGridListener;
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
        checkForAvailablePlayers();
    }

    private void checkForAvailablePlayers() {
        availableUsersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (dsp.getValue(Boolean.class) != null) {
                        myself.setOpponentId(dsp.getKey());
                        myReference.child(OPPONENT_ID).setValue(myself.getOpponentId());
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

    private void makeMatch() {
        if (myself.getOpponentId() == null) {
            availableUsersReference.child(myself.getId()).setValue(true);
            waitingForOpponentDialog.show();
            myReference.child(OPPONENT_ID).addValueEventListener(waitingForOpponentListener);
        } else {
            sudoku = new Sudoku(numOfCellDrop);
            playersReference.child(myself.getOpponentId()).child(OPPONENT_ID).setValue(myself.getId());
            myReference.child(SUDOKU_GRID).setValue(new SerializedSudoku(sudoku));
            startGame();
        }
    }

    @Override
    protected void onKeyPressed() {
        updateMyNumLeft();
    }

    @Override
    protected void initSudokuBoard() {
        // do nothing here
    }

    @Override
    protected void startGame() {
        super.startGame();
        playersReference.child(myself.getOpponentId()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                opponentName = dataSnapshot.getValue(String.class);
                updateMyNumLeft();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myself.setCellsLeft(sudoku.getNumLeft());
        playersReference.child(myself.getOpponentId()).child(CELLS_LEFT).addValueEventListener(opponentNumCellsLeftChangeListener);
        myReference.child(OPPONENT_ID).addValueEventListener(opponentQuitListener);
    }

    private void updateMyNumLeft() {
        myReference.child(CELLS_LEFT).setValue(sudoku.getNumLeft());
        ownNumLeft.setText("You : " + sudoku.getNumLeft());

        if (sudoku.getNumLeft() == 0)
            showWinDialog();
    }

    /**
     * Calls the hierarchy of initialise methods and sets ups all fields correctly
     */
    @Override
    protected void initialiseComponents() {
        super.initialiseComponents();
        ownNumLeft = findViewById(R.id.own_num_left);
        otherNumLeft = findViewById(R.id.other_num_left);
        myProfile = Profile.getCurrentProfile();
        myself = new Player(myProfile.getId(), myProfile.getName(), 81);

        initialiseDialogs();
        initialiseDBComponents();
        initialiseListeners();
    }

    private void initialiseDialogs() {
        waitingForOpponentDialog = new ProgressDialog(this);
        waitingForOpponentDialog.setTitle(PROGRESS_DIALOG_TITLE);
        waitingForOpponentDialog.setMessage(PROGRESS_DIALOG_MESSAGE);
        waitingForOpponentDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        availableUsersReference.child(myself.getId()).setValue(null);
                        myReference.child(OPPONENT_ID).removeEventListener(waitingForOpponentListener);
                        dialog.cancel();
                        finish();
                    }
                });

        quitDialog = new AlertDialog.Builder(PlayOnlineActivity.this);
        quitDialog.setMessage(getString(R.string.quit_prompt));

        quitDialog.setPositiveButton(HomeActivity.POSITIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playersReference.child(myself.getOpponentId()).child(OPPONENT_ID).setValue(null);
                finish();
            }
        });

        quitDialog.setNegativeButton(HomeActivity.NEGATIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

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

    private void initialiseListeners() {
        waitingForOpponentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String opponentId = dataSnapshot.getValue(String.class);
                if (opponentId != null) {
                    myself.setOpponentId(opponentId);
                    myReference.child(OPPONENT_ID).removeEventListener(waitingForOpponentListener);
                    playersReference.child(myself.getOpponentId()).child(SUDOKU_GRID).addValueEventListener(readSudokuGridListener);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        readSudokuGridListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SerializedSudoku serializedSudoku = dataSnapshot.getValue(SerializedSudoku.class);
                if (serializedSudoku != null) {
                    playersReference.child(myself.getOpponentId()).child(SUDOKU_GRID).removeEventListener(readSudokuGridListener);
                    sudoku = new Sudoku(serializedSudoku);
                    waitingForOpponentDialog.cancel();
                    availableUsersReference.child(myself.getId()).setValue(null);
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

    private void initialiseDBComponents() {
        rootReference = FirebaseDatabase.getInstance().getReference();
        availableUsersReference = rootReference.child("available");
        playersReference = rootReference.child("players");
        myReference = playersReference.child(Profile.getCurrentProfile().getId());
        myReference.setValue(myself);
    }

    @Override
    protected void setButtonListeners() {
        negativeButton.setVisibility(View.INVISIBLE);

        positiveButton.setText("Quit");
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuitAlert();
            }
        });
    }

    /**
     * When the user presses the back button he/she quits the game and the opponent automatically wins.
     */
    @Override
    public void onBackPressed() {
        showQuitAlert();
    }

    private void showQuitAlert() {
        quitDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}