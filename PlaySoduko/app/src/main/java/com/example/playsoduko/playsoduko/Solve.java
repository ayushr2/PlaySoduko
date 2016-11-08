package com.example.playsoduko.playsoduko;

/**
 * Created by ayushranjan on 30/10/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Solve extends AppCompatActivity{

    public static int CURRENT_NUMBER = 0;
    public static Button CURRENT_BUTTON = null;
    public static int i;
    public static int j;
    public static int[][] soduko = new int[][]{
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
    };


    //REMOVE THIS!!! JUST FOR TEST
    public static String testing(int[][] pass){
        String result = "";
        result += "[";
        for(int i = 0; i < pass.length; i++){
            result += "[";
            for (int j = 0; j < pass[0].length;j++){
                result += "" + pass[i][j];
            }
            result += "]\n";
        }
        result += "]";
        return result;
    }

    //REMOVE THIS
    public static String testing1(int[] pass){
        String result = "";
        result += "[";
        for(int i = 0; i < pass.length; i++){
            result += "" + pass[i];
        }
        result += "]";
        return result;
    }

    public static void update(){
        if(CURRENT_BUTTON != null){
            soduko[i][j] = CURRENT_NUMBER;
            if(CURRENT_NUMBER == 0)
                CURRENT_BUTTON.setText("");
            else
                CURRENT_BUTTON.setText("" + CURRENT_NUMBER);
        }
    }

    public static void updateSolve(){
        soduko[i][j] = CURRENT_NUMBER;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_layout);

        final Button b00 = (Button)findViewById(R.id.index00);
        final Button b01 = (Button)findViewById(R.id.index01);
        final Button b02 = (Button)findViewById(R.id.index02);
        final Button b03 = (Button)findViewById(R.id.index03);
        final Button b04 = (Button)findViewById(R.id.index04);
        final Button b05 = (Button)findViewById(R.id.index05);
        final Button b06 = (Button)findViewById(R.id.index06);
        final Button b07 = (Button)findViewById(R.id.index07);
        final Button b08 = (Button)findViewById(R.id.index08);
        final Button b10 = (Button)findViewById(R.id.index10);
        final Button b11 = (Button)findViewById(R.id.index11);
        final Button b12 = (Button)findViewById(R.id.index12);
        final Button b13 = (Button)findViewById(R.id.index13);
        final Button b14 = (Button)findViewById(R.id.index14);
        final Button b15 = (Button)findViewById(R.id.index15);
        final Button b16 = (Button)findViewById(R.id.index16);
        final Button b17 = (Button)findViewById(R.id.index17);
        final Button b18 = (Button)findViewById(R.id.index18);
        final Button b20 = (Button)findViewById(R.id.index20);
        final Button b21 = (Button)findViewById(R.id.index21);
        final Button b22 = (Button)findViewById(R.id.index22);
        final Button b23 = (Button)findViewById(R.id.index23);
        final Button b24 = (Button)findViewById(R.id.index24);
        final Button b25 = (Button)findViewById(R.id.index25);
        final Button b26 = (Button)findViewById(R.id.index26);
        final Button b27 = (Button)findViewById(R.id.index27);
        final Button b28 = (Button)findViewById(R.id.index28);
        final Button b30 = (Button)findViewById(R.id.index30);
        final Button b31 = (Button)findViewById(R.id.index31);
        final Button b32 = (Button)findViewById(R.id.index32);
        final Button b33 = (Button)findViewById(R.id.index33);
        final Button b34 = (Button)findViewById(R.id.index34);
        final Button b35 = (Button)findViewById(R.id.index35);
        final Button b36 = (Button)findViewById(R.id.index36);
        final Button b37 = (Button)findViewById(R.id.index37);
        final Button b38 = (Button)findViewById(R.id.index38);
        final Button b40 = (Button)findViewById(R.id.index40);
        final Button b41 = (Button)findViewById(R.id.index41);
        final Button b42 = (Button)findViewById(R.id.index42);
        final Button b43 = (Button)findViewById(R.id.index43);
        final Button b44 = (Button)findViewById(R.id.index44);
        final Button b45 = (Button)findViewById(R.id.index45);
        final Button b46 = (Button)findViewById(R.id.index46);
        final Button b47 = (Button)findViewById(R.id.index47);
        final Button b48 = (Button)findViewById(R.id.index48);
        final Button b50 = (Button)findViewById(R.id.index50);
        final Button b51 = (Button)findViewById(R.id.index51);
        final Button b52 = (Button)findViewById(R.id.index52);
        final Button b53 = (Button)findViewById(R.id.index53);
        final Button b54 = (Button)findViewById(R.id.index54);
        final Button b55 = (Button)findViewById(R.id.index55);
        final Button b56 = (Button)findViewById(R.id.index56);
        final Button b57 = (Button)findViewById(R.id.index57);
        final Button b58 = (Button)findViewById(R.id.index58);
        final Button b60 = (Button)findViewById(R.id.index60);
        final Button b61 = (Button)findViewById(R.id.index61);
        final Button b62 = (Button)findViewById(R.id.index62);
        final Button b63 = (Button)findViewById(R.id.index63);
        final Button b64 = (Button)findViewById(R.id.index64);
        final Button b65 = (Button)findViewById(R.id.index65);
        final Button b66 = (Button)findViewById(R.id.index66);
        final Button b67 = (Button)findViewById(R.id.index67);
        final Button b68 = (Button)findViewById(R.id.index68);
        final Button b70 = (Button)findViewById(R.id.index70);
        final Button b71 = (Button)findViewById(R.id.index71);
        final Button b72 = (Button)findViewById(R.id.index72);
        final Button b73 = (Button)findViewById(R.id.index73);
        final Button b74 = (Button)findViewById(R.id.index74);
        final Button b75 = (Button)findViewById(R.id.index75);
        final Button b76 = (Button)findViewById(R.id.index76);
        final Button b77 = (Button)findViewById(R.id.index77);
        final Button b78 = (Button)findViewById(R.id.index78);
        final Button b80 = (Button)findViewById(R.id.index80);
        final Button b81 = (Button)findViewById(R.id.index81);
        final Button b82 = (Button)findViewById(R.id.index82);
        final Button b83 = (Button)findViewById(R.id.index83);
        final Button b84 = (Button)findViewById(R.id.index84);
        final Button b85 = (Button)findViewById(R.id.index85);
        final Button b86 = (Button)findViewById(R.id.index86);
        final Button b87 = (Button)findViewById(R.id.index87);
        final Button b88 = (Button)findViewById(R.id.index88);
        final Button clearB = (Button)findViewById(R.id.clearbutton);
        final Button solveB = (Button)findViewById(R.id.gobutton);

        solveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soduko = Soduko.solveSoduko(soduko);
                if(Soduko.solved){
                    b00.setText("" + soduko[0][0]);
                    b01.setText("" + soduko[0][1]);
                    b02.setText("" + soduko[0][2]);
                    b03.setText("" + soduko[0][3]);
                    b04.setText("" + soduko[0][4]);
                    b05.setText("" + soduko[0][5]);
                    b06.setText("" + soduko[0][6]);
                    b07.setText("" + soduko[0][7]);
                    b08.setText("" + soduko[0][8]);
                    b10.setText("" + soduko[1][0]);
                    b11.setText("" + soduko[1][1]);
                    b12.setText("" + soduko[1][2]);
                    b13.setText("" + soduko[1][3]);
                    b14.setText("" + soduko[1][4]);
                    b15.setText("" + soduko[1][5]);
                    b16.setText("" + soduko[1][6]);
                    b17.setText("" + soduko[1][7]);
                    b18.setText("" + soduko[1][8]);
                    b20.setText("" + soduko[2][0]);
                    b21.setText("" + soduko[2][1]);
                    b22.setText("" + soduko[2][2]);
                    b23.setText("" + soduko[2][3]);
                    b24.setText("" + soduko[2][4]);
                    b25.setText("" + soduko[2][5]);
                    b26.setText("" + soduko[2][6]);
                    b27.setText("" + soduko[2][7]);
                    b28.setText("" + soduko[2][8]);
                    b30.setText("" + soduko[3][0]);
                    b31.setText("" + soduko[3][1]);
                    b32.setText("" + soduko[3][2]);
                    b33.setText("" + soduko[3][3]);
                    b34.setText("" + soduko[3][4]);
                    b35.setText("" + soduko[3][5]);
                    b36.setText("" + soduko[3][6]);
                    b37.setText("" + soduko[3][7]);
                    b38.setText("" + soduko[3][8]);
                    b40.setText("" + soduko[4][0]);
                    b41.setText("" + soduko[4][1]);
                    b42.setText("" + soduko[4][2]);
                    b43.setText("" + soduko[4][3]);
                    b44.setText("" + soduko[4][4]);
                    b45.setText("" + soduko[4][5]);
                    b46.setText("" + soduko[4][6]);
                    b47.setText("" + soduko[4][7]);
                    b48.setText("" + soduko[4][8]);
                    b50.setText("" + soduko[5][0]);
                    b51.setText("" + soduko[5][1]);
                    b52.setText("" + soduko[5][2]);
                    b53.setText("" + soduko[5][3]);
                    b54.setText("" + soduko[5][4]);
                    b55.setText("" + soduko[5][5]);
                    b56.setText("" + soduko[5][6]);
                    b57.setText("" + soduko[5][7]);
                    b58.setText("" + soduko[5][8]);
                    b60.setText("" + soduko[6][0]);
                    b61.setText("" + soduko[6][1]);
                    b62.setText("" + soduko[6][2]);
                    b63.setText("" + soduko[6][3]);
                    b64.setText("" + soduko[6][4]);
                    b65.setText("" + soduko[6][5]);
                    b66.setText("" + soduko[6][6]);
                    b67.setText("" + soduko[6][7]);
                    b68.setText("" + soduko[6][8]);
                    b70.setText("" + soduko[7][0]);
                    b71.setText("" + soduko[7][1]);
                    b72.setText("" + soduko[7][2]);
                    b73.setText("" + soduko[7][3]);
                    b74.setText("" + soduko[7][4]);
                    b75.setText("" + soduko[7][5]);
                    b76.setText("" + soduko[7][6]);
                    b77.setText("" + soduko[7][7]);
                    b78.setText("" + soduko[7][8]);
                    b80.setText("" + soduko[8][0]);
                    b81.setText("" + soduko[8][1]);
                    b82.setText("" + soduko[8][2]);
                    b83.setText("" + soduko[8][3]);
                    b84.setText("" + soduko[8][4]);
                    b85.setText("" + soduko[8][5]);
                    b86.setText("" + soduko[8][6]);
                    b87.setText("" + soduko[8][7]);
                    b88.setText("" + soduko[8][8]);
                    Soduko.solved = false;
                }

                else{
                    Toast toast = Toast.makeText(Solve.this, "This Soduko does not have a unique solution!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });


        b00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b00;
                i = 0;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b01;
                i = 0;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b02;
                i = 0;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b03;
                i = 0;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b04;
                i = 0;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b05;
                i = 0;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b06;
                i = 0;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b07;
                i = 0;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b08;
                i = 0;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b10;
                i = 1;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b11;
                i = 1;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b12;
                i = 1;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b13;
                i = 1;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b14;
                i = 1;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b15;
                i = 1;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b16;
                i = 1;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b17;
                i = 1;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b18;
                i = 1;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b20;
                i = 2;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b21;
                i = 2;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b22;
                i = 2;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b23;
                i = 2;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b24;
                i = 2;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b25;
                i = 2;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b26;
                i = 2;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b27;
                i = 2;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b28;
                i = 2;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b30;
                i = 3;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b31;
                i = 3;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b32;
                i = 3;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b33;
                i = 3;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b34;
                i = 3;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b35;
                i = 3;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b36;
                i = 3;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b37;
                i = 3;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b38;
                i = 3;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b40;
                i = 4;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b41;
                i = 4;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b42;
                i = 4;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b43;
                i = 4;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b44;
                i = 4;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b45;
                i = 4;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b46;
                i = 4;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b47;
                i = 4;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b48;
                i = 4;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b50;
                i = 5;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b51;
                i = 5;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b52;
                i = 5;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b53;
                i = 5;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b54;
                i = 5;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b55;
                i = 5;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b56;
                i = 5;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b57;
                i = 5;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b58;
                i = 5;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b60;
                i = 6;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b61;
                i = 6;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b62;
                i = 6;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b63;
                i = 6;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b64;
                i = 6;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b65;
                i = 6;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b66;
                i = 6;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b67;
                i = 6;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b68;
                i = 6;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b70;
                i = 7;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b71;
                i = 7;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b72;
                i = 7;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b73;
                i = 7;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b74;
                i = 7;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b75;
                i = 7;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b76;
                i = 7;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b77;
                i = 7;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b78.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b78;
                i = 7;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b80;
                i = 8;
                j = 0;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b81;
                i = 8;
                j = 1;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b82;
                i = 8;
                j = 2;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b83;
                i = 8;
                j = 3;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b84;
                i = 8;
                j = 4;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b85;
                i = 8;
                j = 5;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b86.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b86;
                i = 8;
                j = 6;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b87.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b87;
                i = 8;
                j = 7;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        b88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_BUTTON = b88;
                i = 8;
                j = 8;
                Intent intent = new Intent(Solve.this,Pop.class);
                startActivity(intent);
            }
        });

        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soduko = new int[][]{
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                };
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
}