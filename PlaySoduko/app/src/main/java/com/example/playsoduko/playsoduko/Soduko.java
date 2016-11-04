package com.example.playsoduko.playsoduko;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by ayushranjan on 02/11/16.
 */

public class Soduko {

    public static boolean solved = false;

    public static String buttonsNotAvailable(int index1, int index2){
        String answer = "";

        //search column
        for(int i = 0; i < 9; i++){
            answer += "" + Solve.soduko[i][index2];
        }

        //search row
        for(int i = 0; i < 9; i++){
            answer += "" + Solve.soduko[index1][i];
        }

        //search grid
        if(0 <= index1 && index1 <= 2 && 0 <= index2 && index2 <= 2){
            for(int i = 0; i < 3 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 3 <= index2 && index2 <= 5){
            for(int i = 0; i < 3 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 6 <= index2 && index2 <= 8){
            for(int i = 0; i < 3 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 0 <= index2 && index2 <= 2){
            for(int i = 3; i < 6 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 3 <= index2 && index2 <= 5){
            for(int i = 3; i < 6 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 6 <= index2 && index2 <= 8){
            for(int i = 3; i < 6 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 0 <= index2 && index2 <= 2){
            for(int i = 6; i < 9 ; i++){
                for(int j = 0; j < 3; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 3 <= index2 && index2 <= 5){
            for(int i = 6; i < 9 ; i++){
                for(int j = 3; j < 6; j++){
                    answer += "" + Solve.soduko[i][j];
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 6 <= index2 && index2 <= 8){
            for(int i = 6; i < 9 ; i++){
                for(int j = 6; j < 9; j++){
                    answer += "" + Solve.soduko[i][j];
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

    public static int[][] deepCopy(int[][] array){
        int[][] answer = new int[array.length][array[0].length];
        for(int i = 0; i < 9 ; i++){
            for(int j = 0; j < 9; j++){
                answer[i][j] = array[i][j];
            }
        }
        return answer;
    }

    public static void solveSoduko(){
        int[][] initial = deepCopy(Solve.soduko);
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
        Log.d("number of zeroes", "" + numberOf0);
        while(numberOf0 > 0){
            for(int i = 0; i < 9 ; i++){
                for(int j = 0; j < 9; j++){
                    String used = buttonsNotAvailable(i,j);
                    if(used.length() == 8 && Solve.soduko[i][j] == 0){
                        int sum = 0;
                        for(int index = 0; index < used.length();index++){
                            sum += Integer.parseInt("" + used.charAt(index));
                        }
                        Solve.CURRENT_NUMBER = 45-sum;
                        Solve.i = i;
                        Solve.j = j;
                        Solve.updateSolve();
                        numberOf0--;
                    }
                    else{
                    }
                }
            }

            if(numberOf0 == 0)
                solved = true;
            Log.d("ANSWER!!!!!",Solve.testing(Solve.soduko));

            numOfReps++;
            if(numOfReps > initialNumberOf0 && numberOf0 > 0){
                //cannot solve
                Solve.soduko = initial;
                return;
            }
        }

    }
}
