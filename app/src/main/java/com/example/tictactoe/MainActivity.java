package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    /* Player representation :
                               0 - X
                               1 - O  */
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    /* State meaning :
                       0 - X
                       1 - O
                       2 - NULL */

    int[][] winPositions= {{0,1,2}, {3,4,5}, {6,7,8},
                           {0,3,6}, {1,4,7}, {2,5,8},
                           {0,4,8}, {2,4,6}};


    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString()); // parseInt use for conversion from string to integer
        img.setTranslationY(-1000f);
        if(!gameActive)
        {
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O'turn : Tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X'turn : Tap to Play");
            }
        }
        img.animate().translationYBy(1000f).setDuration(300);

        // check if player has won :
        for (int[] winPosition: winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2)
            // somebody has won find out who
            {
                gameActive = false;
                String winnerStr;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "Congratulation X has Won..!!";
                }
                else
                {
                    winnerStr = "Congratulation O has Won..!!";
                }

                // update the status for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState,2);

        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}