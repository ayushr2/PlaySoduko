package com.ayush.playsoduko.playsoduko;

import android.support.annotation.NonNull;

import com.ayush.playsoduko.playsoduko.firebase_objects.Player;
import com.ayush.playsoduko.playsoduko.utilities.Sudoku;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

/**
 * This class contains all the tests relating to my app.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatabaseTest {

    public static final String USER_ID_1 = "1234563";
    public static final String USER_ID_2 = "1256987";
    public static final String MY_NAME = "Ayush";
    public static final String OPPONENT_NAME = "Trump";
    public static final String PARENT_ARRAY = "players";
    public static final String CELLS_LEFT_TAG = "cellsLeft";
    private static final int CHANGED_VALUE = 500;
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    /**
     * This test demonstrated writing and reading from the data base. I am writing two Player objects to
     * the DB and then sets an ValueEventListener to the opponent object. Whenever the opponent is changed
     * it sets its own value to the changed value.
     *
     * @throws InterruptedException
     */
    @Test
    public void gettingUserInfoTest() throws InterruptedException {
        final CountDownLatch writeSignal = new CountDownLatch(2);

        final Player myself = new Player(USER_ID_1, MY_NAME, new Sudoku(10));
        final Player opponent = new Player(USER_ID_2, OPPONENT_NAME, new Sudoku(10));

        // writing my object to the DB
        reference.child(PARENT_ARRAY).child(String.valueOf(myself.getId()))
                .setValue(myself).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        // writing opponent object to the DB
        reference.child(PARENT_ARRAY).child(String.valueOf(opponent.getId()))
                .setValue(opponent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        // setting listener to opponent object. Sets the opponent object's cells left to the changed value
        ValueEventListener opponentChange = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player opponentChanged = dataSnapshot.getValue(Player.class);
                opponent.setCellsLeft(opponentChanged.getCellsLeft());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.child(PARENT_ARRAY).child(String.valueOf(opponent.getId()))
                .addValueEventListener(opponentChange);

        // changing the opponent child's value
        reference.child(PARENT_ARRAY).child(String.valueOf(opponent.getId())).child(CELLS_LEFT_TAG)
                .setValue(CHANGED_VALUE);

        writeSignal.await(10, TimeUnit.SECONDS);

        // test assert
        assertEquals(opponent.getCellsLeft(), CHANGED_VALUE);
    }

    /**
     * This method creates a random sudoku. captures it. Then randomly drops few cells from it. Finally
     * it runs the solve algorithm on it and captures the output. Then compares the two sudokus to see
     * if they are the same.
     *
     * @throws Exception
     */
    @Test
    public void testSolve() throws Exception {
        Sudoku test = new Sudoku();
        test.random();
        int[][] answer = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(test.getGrid()[i], 0, answer[i], 0, 9);
        }
        test.dropCells(30);
        test.solve();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (test.getGrid()[i][j] != answer[i][j]) {
                    throw new Exception("failed");
                }
            }
        }
    }
}
