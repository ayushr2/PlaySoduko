package com.example.playsoduko.playsoduko;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by ayushranjan on 02/11/16.
 */

public class Soduko {

    public static boolean solved = false;

    public static String buttonsNotAvailable(int[][] grid, int index1, int index2){
        String answer = "";

        //search column
        for(int i = 0; i < 9; i++){
            answer += "" + grid[i][index2];
        }

        //search row
        for(int i = 0; i < 9; i++){
            answer += "" + grid[index1][i];
        }

        //search grid
        if(0 <= index1 && index1 <= 2 && 0 <= index2 && index2 <= 2){
            for(int i = 0; i < 3 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 3 <= index2 && index2 <= 5){
            for(int i = 0; i < 3 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 6 <= index2 && index2 <= 8){
            for(int i = 0; i < 3 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 0 <= index2 && index2 <= 2){
            for(int i = 3; i < 6 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 3 <= index2 && index2 <= 5){
            for(int i = 3; i < 6 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 6 <= index2 && index2 <= 8){
            for(int i = 3; i < 6 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 0 <= index2 && index2 <= 2){
            for(int i = 6; i < 9 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 3 <= index2 && index2 <= 5){
            for(int i = 6; i < 9 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 6 <= index2 && index2 <= 8){
            for(int i = 6; i < 9 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        String finalS = "";
        if(answer.indexOf('1') >= 0)
            finalS += "1";
        if(answer.indexOf('2') >= 0)
            finalS += "2";
        if(answer.indexOf('3') >= 0)
            finalS += "3";
        if(answer.indexOf('4') >= 0)
            finalS += "4";
        if(answer.indexOf('5') >= 0)
            finalS += "5";
        if(answer.indexOf('6') >= 0)
            finalS += "6";
        if(answer.indexOf('7') >= 0)
            finalS += "7";
        if(answer.indexOf('8') >= 0)
            finalS += "8";
        if(answer.indexOf('9') >= 0)
            finalS += "9";
        return finalS;
    }

    public static String buttonsAvailable(int[][] grid, int index1, int index2){
        String answer = "";

        //search column
        for(int i = 0; i < 9; i++){
            answer += "" + grid[i][index2];
        }

        //search row
        for(int i = 0; i < 9; i++){
            answer += "" + grid[index1][i];
        }

        //search grid
        if(0 <= index1 && index1 <= 2 && 0 <= index2 && index2 <= 2){
            for(int i = 0; i < 3 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 3 <= index2 && index2 <= 5){
            for(int i = 0; i < 3 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 6 <= index2 && index2 <= 8){
            for(int i = 0; i < 3 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 0 <= index2 && index2 <= 2){
            for(int i = 3; i < 6 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 3 <= index2 && index2 <= 5){
            for(int i = 3; i < 6 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 6 <= index2 && index2 <= 8){
            for(int i = 3; i < 6 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 0 <= index2 && index2 <= 2){
            for(int i = 6; i < 9 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 3 <= index2 && index2 <= 5){
            for(int i = 6; i < 9 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 6 <= index2 && index2 <= 8){
            for(int i = 6; i < 9 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + grid[i][j];
                }
            }
        }

        String finalS = "";
        if(answer.indexOf('1') < 0)
            finalS += "1";
        if(answer.indexOf('2') < 0)
            finalS += "2";
        if(answer.indexOf('3') < 0)
            finalS += "3";
        if(answer.indexOf('4') < 0)
            finalS += "4";
        if(answer.indexOf('5') < 0)
            finalS += "5";
        if(answer.indexOf('6') < 0)
            finalS += "6";
        if(answer.indexOf('7') < 0)
            finalS += "7";
        if(answer.indexOf('8') < 0)
            finalS += "8";
        if(answer.indexOf('9') < 0)
            finalS += "9";
        return finalS;
    }

    public static int[][] deepCopy(int[][] array){
        int[][] answer = new int[array.length][array[0].length];
        for(int i = 0; i < 9 ; i++){
            for(int j = 0; j < 9; j++){
                answer[i][j] = array[i][j];
            }
        }
        return answer;
    }

    public static int[][] solveSoduko(int[][] grid){
        int[][] initial = deepCopy(grid);
        int numberOf0 = 0;
        int numOfReps = 0;
        for(int index1 = 0; index1 < 9 ; index1++){
            for(int index2 = 0; index2 < 9; index2++){
                if(initial[index1][index2] == 0){
                    numberOf0++;
                }
            }
        }
        int initialNumberOf0 = numberOf0;

        while(numberOf0 > 0){
            for(int i = 0; i < 9 ; i++){
                for(int j = 0; j < 9; j++){
                    if(grid[i][j] == 0) {
                        String used = buttonsNotAvailable(grid, i, j);

                        if (used.length() == 8 && grid[i][j] == 0) {
                            int sum = 0;
                            for (int index = 0; index < used.length(); index++) {
                                sum += Integer.parseInt("" + used.charAt(index));
                            }
                            grid[i][j] = 45 - sum;
                            Log.d("CURRENT GRID", Solve.testing(grid));
                            Log.d("I VALUE", "" + i);
                            Log.d("J VALUE", "" + j);
                            numberOf0--;
                            break;
                        }

                        used = buttonsAvailable(grid, i, j);

                        if (grid[i][j] == 0) {
                            String[] compareColumn = {"a", "a", "a", "a", "a", "a", "a", "a", "a"};
                            for (int row = 0; row < 9; row++) {
                                if (grid[row][j] == 0 && row != i)
                                    compareColumn[row] = buttonsAvailable(grid, row, j);
                            }
                            for (int number = 0; number < used.length(); number++) {
                                if ((compareColumn[0].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[1].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[2].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[3].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[4].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[5].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[6].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[7].indexOf(used.charAt(number)) < 0) &&
                                        (compareColumn[8].indexOf(used.charAt(number)) < 0)) {
                                    grid[i][j] = Integer.parseInt("" + used.charAt(number));
                                    numberOf0--;
                                    break;
                                }
                            }
                        }
                        if (grid[i][j] == 0) {
                            String[] compareRow = {"a", "a", "a", "a", "a", "a", "a", "a", "a"};
                            for (int column = 0; column < 9; column++) {
                                if (grid[i][column] == 0 && column != j)
                                    compareRow[column] = buttonsAvailable(grid, i, column);
                            }
                            for (int number = 0; number < used.length(); number++) {
                                if ((compareRow[0].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[1].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[2].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[3].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[4].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[5].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[6].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[7].indexOf(used.charAt(number)) < 0) &&
                                        (compareRow[8].indexOf(used.charAt(number)) < 0)) {
                                    grid[i][j] = Integer.parseInt("" + used.charAt(number));
                                    numberOf0--;
                                    break;
                                }
                            }
                        }

                        if (grid[i][j] == 0) {
                            String[] compareGrid = {"a", "a", "a", "a", "a", "a", "a", "a", "a"};

                            if (0 <= i && i <= 2 && 0 <= j && j <= 2) {
                                int count = 0;
                                for (int count1 = 0; count1 < 3; count1++) {
                                    for (int count2 = 0; count2 < 3; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (0 <= i && i <= 2 && 3 <= j && j <= 5) {
                                int count = 0;
                                for (int count1 = 0; count1 < 3; count1++) {
                                    for (int count2 = 3; count2 < 6; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (0 <= i && i <= 2 && 6 <= j && j <= 8) {
                                int count = 0;
                                for (int count1 = 0; count1 < 3; count1++) {
                                    for (int count2 = 6; count2 < 9; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (3 <= i && i <= 5 && 0 <= j && j <= 2) {
                                int count = 0;
                                for (int count1 = 3; count1 < 6; count1++) {
                                    for (int count2 = 0; count2 < 3; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (3 <= i && i <= 5 && 3 <= j && j <= 5) {
                                int count = 0;
                                for (int count1 = 3; count1 < 6; count1++) {
                                    for (int count2 = 3; count2 < 6; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (3 <= i && i <= 5 && 6 <= j && j <= 8) {
                                int count = 0;
                                for (int count1 = 3; count1 < 6; count1++) {
                                    for (int count2 = 6; count2 < 9; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (6 <= i && i <= 8 && 0 <= j && j <= 2) {
                                int count = 0;
                                for (int count1 = 6; count1 < 9; count1++) {
                                    for (int count2 = 0; count2 < 3; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (6 <= i && i <= 8 && 3 <= j && j <= 5) {
                                int count = 0;
                                for (int count1 = 6; count1 < 9; count1++) {
                                    for (int count2 = 3; count2 < 6; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            if (6 <= i && i <= 8 && 6 <= j && j <= 8) {
                                int count = 0;
                                for (int count1 = 6; count1 < 9; count1++) {
                                    for (int count2 = 6; count2 < 9; count2++) {
                                        if (grid[count1][count2] == 0 && count1 != i && count2 != j) {
                                            compareGrid[count] = buttonsAvailable(grid, count1, count2);
                                            count++;
                                        }
                                    }
                                }
                            }

                            for (int number = 0; number < used.length(); number++) {
                                if ((compareGrid[0].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[1].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[2].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[3].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[4].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[5].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[6].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[7].indexOf(used.charAt(number)) < 0) &&
                                        (compareGrid[8].indexOf(used.charAt(number)) < 0)) {
                                    grid[i][j] = Integer.parseInt("" + used.charAt(number));
                                    numberOf0--;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if(numberOf0 == 0){
                solved = true;
                return grid;
            }
            Log.d("ANSWER!!!!!",Solve.testing(grid));

            numOfReps++;
            if(numOfReps > initialNumberOf0 && numberOf0 > 0){
                //cannot solve
                return initial;
            }
        }
        return grid;
    }

    public static int[][] random(){
        int[][] puzzle = {{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}};
        main : while(true){
            trial : for(int i = 0; i < 9;i++){
                for(int j = 0; j < 9; j++){
                    String s = buttonsAvailable(puzzle,i,j);
                    if(s.equals("")){
                        int[][] nuzzle = {{0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0}};
                        puzzle = nuzzle;
                        break trial;
                    }
                    int index = (int) (Math.random()*s.length());
                    puzzle[i][j] = Integer.parseInt("" + s.charAt(index));
                    if(i == 8 && j == 8)
                        break main;
                }
            }
        }
        return puzzle;
    }
}
