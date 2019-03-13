package com.aravind.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player = 0 ;

    boolean gameActive = true;

    int[] positions = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{6,7,8},{0,4,8},{2,4,6}};

    void printResult(String message){

        //display player who won the game
        TextView winmes = (TextView)findViewById(R.id.winMessage);

        winmes.setText(message);

        //play again button
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.VISIBLE);
    }

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int clickedCounter = Integer.parseInt(counter.getTag().toString());
        System.out.println(clickedCounter);

        if (positions[clickedCounter] == 2 && gameActive) {

            positions[clickedCounter]= player;

            counter.setTranslationY(-1000f);
            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition : winningPositions){
                if(positions[winningPosition[0]]== positions[winningPosition[1]] && positions[winningPosition[1]]== positions[winningPosition[2]]
                                                                                    && positions[winningPosition[0]] !=2  ){
                    String winner = "Red Player has won!";

                    gameActive = false;

                    if(positions[winningPosition[0]]==0){
                        winner = "Yellow Player has won!";
                    }

                    printResult(winner);
                }
                else{
                    boolean gameOver = true;

                    for(int counterState : positions){

                        if(counterState == 2){
                            gameOver = false;
                        }
                    }

                    if(gameOver){
                        printResult("It's a draw");
                    }
                }
            }
        }
    }

    void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        player = 0;

        gameActive = true;

        for(int i = 0; i < positions.length; i ++){
            positions[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
//    public void fade(View view){
//        ImageView lelouch = (ImageView)findViewById(R.id.lelouch);
//        ImageView zero = (ImageView)findViewById(R.id.zero);
//        lelouch.animate().alpha(0f).setDuration(2000);
//        zero.animate().alpha(1f).setDuration(3000);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
