package com.ayush.playsoduko.playsoduko;

import com.ayush.playsoduko.playsoduko.utilities.Sudoku;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ayushranjan on 05/04/17.
 */
public class SudokuTest extends TestCase {
    public void testRandom() throws Exception {
        Sudoku current = new Sudoku();
        current.random();
        System.out.println(current.toString());
        Sudoku testDrop =  new Sudoku(10);
        System.out.println(testDrop.toString());
    }

    public void testAddAll() throws Exception {
        Set<Integer> possibilitiesOfRow = new HashSet<>();
        Integer[] array1 = {1,1,1,1,1,1,2,3,4,1,1,1,3,3,4};
        Integer[] array2 = {1,2,3,4};
        possibilitiesOfRow.addAll(Arrays.asList(array1));
        possibilitiesOfRow.addAll(Arrays.asList(array2));
        for (Integer a : possibilitiesOfRow) {
            System.out.println(a);
        }
    }

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