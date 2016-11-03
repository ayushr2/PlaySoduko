package com.example.playsoduko.playsoduko;

import java.util.Arrays;

/**
 * Created by ayushranjan on 02/11/16.
 */

public class Soduko {

    private static int[] addList(int[] list, int number){
        int[] answer = new int[list.length+1];
        for(int i = 0; i < list.length; i++){
            answer[i] = list[i];
        }
        answer[list.length] = number;
        return answer;
    }

    public static int[] buttonsNotAvailable(int index1, int index2){
        int[] answer = new int[0];

        //search column
        for(int i = 0; i < 9; i++){
            if(!Arrays.asList(answer).contains(Solve.soduko[i][index2]))
                answer = addList(answer,Solve.soduko[i][index2]);
        }

        //search row
        for(int i = 0; i < 9; i++){
            if(!Arrays.asList(answer).contains(Solve.soduko[index1][i]))
                answer = addList(answer,Solve.soduko[index1][i]);
        }

        //search grid
        if(0 <= index1 && index1 <= 2 && 0 <= index2 && index2 <= 2){
            for(int i = 0; i < 3 ; i++){
                for(int j = 0; j < 3; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 3 <= index2 && index2 <= 5){
            for(int i = 0; i < 3 ; i++){
                for(int j = 3; j < 6; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(0 <= index1 && index1 <= 2 && 6 <= index2 && index2 <= 8){
            for(int i = 0; i < 3 ; i++){
                for(int j = 6; j < 9; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 0 <= index2 && index2 <= 2){
            for(int i = 3; i < 6 ; i++){
                for(int j = 0; j < 3; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 3 <= index2 && index2 <= 5){
            for(int i = 3; i < 6 ; i++){
                for(int j = 3; j < 6; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(3 <= index1 && index1 <= 5 && 6 <= index2 && index2 <= 8){
            for(int i = 3; i < 6 ; i++){
                for(int j = 6; j < 9; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 0 <= index2 && index2 <= 2){
            for(int i = 6; i < 9 ; i++){
                for(int j = 0; j < 3; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 3 <= index2 && index2 <= 5){
            for(int i = 6; i < 9 ; i++){
                for(int j = 3; j < 6; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        if(6 <= index1 && index1 <= 8 && 6 <= index2 && index2 <= 8){
            for(int i = 6; i < 9 ; i++){
                for(int j = 6; j < 9; j++){
                    if(!Arrays.asList(answer).contains(Solve.soduko[i][j]))
                        answer = addList(answer,Solve.soduko[i][j]);
                }
            }
        }

        return answer;

    }
}
