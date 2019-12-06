package com.epizy.tikotest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView player1Name, player2Name, remainingRolls, player1Score, player2Score;
    private ImageView player1Lines, player2Lines, dice1, dice2, dice3;
    private Button rollAgain, endTurn;
    private CheckBox check1, check2, check3;
    private int rolls = 3, min = 1, max = 6, score = 0, stripesPlayer1 = 7, stripesPlayer2 = 7, maxStripes = 7 ,valueDice1, valueDice2, valueDice3, player1Val, player2Val;
    private boolean firstPlayerActive = true;
    private String scoreText, linesToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find views
        player1Name = (TextView) findViewById(R.id.player1Name);
        player2Name = (TextView) findViewById(R.id.player2Name);
        remainingRolls = (TextView) findViewById(R.id.remainingRolls);
        player1Score = (TextView) findViewById(R.id.player1Score);
        player2Score = (TextView) findViewById(R.id.player2Score);


        player1Lines = (ImageView) findViewById(R.id.player1Lines);
        player2Lines = (ImageView) findViewById(R.id.player2Lines);
        dice1 = (ImageView) findViewById(R.id.dice1);
        dice2 = (ImageView) findViewById(R.id.dice2);
        dice3 = (ImageView) findViewById(R.id.dice3);

        check1 = (CheckBox) findViewById(R.id.check1);
        check2 = (CheckBox) findViewById(R.id.check2);
        check3 = (CheckBox) findViewById(R.id.check3);

        rollAgain = (Button) findViewById(R.id.rollAgain);
        endTurn = (Button) findViewById(R.id.endTurn);

        // get intent and user names from login activity
        Intent intent = getIntent();
        String nameOne = intent.getExtras().getString("name1");
        String nameTwo = intent.getExtras().getString("name2");
        player1Name.setText(nameOne);
        player2Name.setText(nameTwo);

        // on click roll dice
        rollAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rolls == 3 && check1.isChecked() && check2.isChecked() && check3.isChecked()){ //check if player rolled at least once
                    rollingDices();
                    scoreBoard();
                    rolls -= 1;
                    remainingRolls.setText("rolls remaining: " + String.valueOf(rolls));
                } else if(rolls == 3 && (!check1.isChecked() || !check2.isChecked() || !check3.isChecked())) {
                    Toast.makeText(getApplicationContext(), "Please select all dices on your first trow" ,Toast.LENGTH_LONG).show();
                } else if(rolls == 0) { //auto end users turn if he clicks roll when he has no rolls left
                    if(firstPlayerActive){
                        Toast.makeText(getApplicationContext(), "Player1 just ended his turn" ,Toast.LENGTH_LONG).show();
                        player1Name.setTextColor(getResources().getColor(R.color.sleepingPlayer));
                        player2Name.setTextColor(getResources().getColor(R.color.activePlayer));
                        firstPlayerActive = false;
                    }else {
                        Toast.makeText(getApplicationContext(), "Player2 just ended his turn" ,Toast.LENGTH_LONG).show();
                        firstPlayerActive = true;
                        player1Name.setTextColor(getResources().getColor(R.color.activePlayer));
                        player2Name.setTextColor(getResources().getColor(R.color.sleepingPlayer));
                        scoreComparison();
                    }
                    rolls = 3;
                    remainingRolls.setText("rolls remaining: " + String.valueOf(rolls));
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(true);
                } else { //regular roll with as many dice as you want
                    rollingDices();
                    scoreBoard();
                    rolls -= 1;
                    remainingRolls.setText("rolls remaining: " + String.valueOf(rolls));
                }
            }
        });

        // on click end turn button
        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check if rolled at least 1 time before passing the turn
                if(rolls == 3){
                    Toast.makeText(getApplicationContext(), "you need to roll at least once before ending your turn" ,Toast.LENGTH_LONG).show();
                }else {
                    if(firstPlayerActive){
                        player1Name.setTextColor(getResources().getColor(R.color.sleepingPlayer));
                        player2Name.setTextColor(getResources().getColor(R.color.activePlayer));
                        firstPlayerActive = false;
                    }else{
                        firstPlayerActive = true;
                        player1Name.setTextColor(getResources().getColor(R.color.activePlayer));
                        player2Name.setTextColor(getResources().getColor(R.color.sleepingPlayer));
                        scoreComparison();
                    }
                    rolls = 3;
                    remainingRolls.setText("rolls remaining: " + String.valueOf(rolls));
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(true);
                }
            }

        });
    }

    // roll dices
    private void rollingDices(){
        for(int i = 0; i <= 3; i++){
            Random r = new Random();
            int randomDiceVal = r.nextInt((max - min) + 1) + min;

            if(i == 1 && check1.isChecked()){
                valueDice1 = randomDiceVal;
                switch (randomDiceVal){
                    case 1:
                        dice1.setBackgroundResource(R.drawable.dice1eye);
                        break;
                    case 2:
                        dice1.setBackgroundResource(R.drawable.dice2eye);
                        break;
                    case 3:
                        dice1.setBackgroundResource(R.drawable.dice3eye);
                        break;
                    case 4:
                        dice1.setBackgroundResource(R.drawable.dice4eye);
                        break;
                    case 5:
                        dice1.setBackgroundResource(R.drawable.dice5eye);
                        break;
                    case 6:
                        dice1.setBackgroundResource(R.drawable.dice6eye);
                        break;
                }
            } else if(i == 2 && check2.isChecked()){
                valueDice2 = randomDiceVal;
                switch (randomDiceVal) {
                    case 1:
                        dice2.setBackgroundResource(R.drawable.dice1eye);
                        break;
                    case 2:
                        dice2.setBackgroundResource(R.drawable.dice2eye);
                        break;
                    case 3:
                        dice2.setBackgroundResource(R.drawable.dice3eye);
                        break;
                    case 4:
                        dice2.setBackgroundResource(R.drawable.dice4eye);
                        break;
                    case 5:
                        dice2.setBackgroundResource(R.drawable.dice5eye);
                        break;
                    case 6:
                        dice2.setBackgroundResource(R.drawable.dice6eye);
                        break;
                }
            } else if (i == 3 && check3.isChecked()){
                valueDice3 = randomDiceVal;
                switch(randomDiceVal) {
                    case 1:
                        dice3.setBackgroundResource(R.drawable.dice1eye);
                        break;
                    case 2:
                        dice3.setBackgroundResource(R.drawable.dice2eye);
                        break;
                    case 3:
                        dice3.setBackgroundResource(R.drawable.dice3eye);
                        break;
                    case 4:
                        dice3.setBackgroundResource(R.drawable.dice4eye);
                        break;
                    case 5:
                        dice3.setBackgroundResource(R.drawable.dice5eye);
                        break;
                    case 6:
                        dice3.setBackgroundResource(R.drawable.dice6eye);
                        break;
                }
            }
        }
    }


    // update score
    private void scoreBoard(){
        // calculate score of the active player
        score = 0;
        // sand
        if(valueDice1 == valueDice2 && valueDice2 == valueDice3){
            switch(valueDice1) {
                case 1:
                    // three monkeys
                    score = 10000;
                    scoreText = "Three monkeys!";
                    break;
                case 2:
                    // sand: 2-2-2
                    score = 4000;
                    scoreText = "Sand 2-2-2";
                    break;
                case 3:
                    // sand: 3-3-3
                    score = 5000;
                    scoreText = "Sand 3-3-3";
                    break;
                case 4:
                    // sand: 4-4-4
                    score = 6000;
                    scoreText = "Sand 4-4-4";
                    break;
                case 5:
                    // sand: 5-5-5
                    score = 7000;
                    scoreText = "Sand 5-5-5";
                    break;
                case 6:
                    // sand: 6-6-6
                    score = 8000;
                    scoreText = "Sand 6-6-6";
                    break;
            }
        }else if( score == 0){
            if((valueDice1 == 4 || valueDice2 == 4 || valueDice3 == 4) && (valueDice1 == 5 || valueDice2 == 5 || valueDice3 == 5) && (valueDice1 == 6 || valueDice2 == 6 || valueDice3 == 6)){
                // Soixante-neuf 6-5-4
                score = 9000;
                scoreText = "Soixante-neuf 6-5-4";
            }
            // others = som dices (where dice val 1 = 100)
            else{
                scoreText = "regular score " + valueDice1 + "-" + valueDice2 + "-" + valueDice3;
                if(valueDice1 == 1){
                    valueDice1 = 100;
                }
                if(valueDice2 == 1){
                    valueDice2 = 100;
                }
                if(valueDice3 == 1){
                    valueDice3 = 100;
                }
                if(valueDice1 == 6){
                    valueDice1 = 60;
                }
                if(valueDice2 == 6){
                    valueDice2 = 60;
                }
                if(valueDice3 == 6){
                    valueDice3 = 60;
                }
                score = valueDice1 + valueDice2 + valueDice3;
                // special case 7 in others 2-2-3
                if(score == 7){
                    scoreText = "lowest score 2-2-3";
                    stripesPlayer1 += 1;
                    stripesPlayer2 += 1;
                    player1LineUpdate();
                    player2LineUpdate();
                    showWinner("the lowest number was thrown!", "both players get 1 extra line");
                }
            }
        }
        updateScore();
    }

    // update score board of player
    private void updateScore() {
        if(firstPlayerActive){
            player1Val = score;
            player1Score.setText("score: " + score + "\n" + scoreText);
        }else {
            player2Val = score;
            player2Score.setText("score: " + score + "\n" + scoreText);
        }
        score = 0;
        scoreText = "test";
    }

    private void scoreComparison() {
        // check is scores are equal (ex aequo)
        if(player1Val == player2Val) {
            // in tie play another round
            showWinner("you got the same score", "Play again to decide who wins");
        }else if(player1Val > player2Val){
            switch (player1Val){
                // three monkeys
                case 10000:
                    if(stripesPlayer1 >= maxStripes){
                        // you lose
                        showGameWinner(player2Name.getText() + "won!", player1Name.getText() + " threw three monkeys before swiping away one of his lines and that makes him the big loser of the game.");
                    }else{
                        //you win
                        linesToRemove = "all";
                        stripesPlayer1 = 0;
                    }
                    break;
                //Soixante neuf
                case 9000:
                    linesToRemove = "three";
                    stripesPlayer1 -= 3;
                    break;
                //sand
                case 8000:
                case 7000:
                case 6000:
                case 5000:
                case 4000:
                    linesToRemove = "two";
                    stripesPlayer1 -= 2;
                    break;
                default:
                    //remove 1 stripe
                    linesToRemove = "one";
                    stripesPlayer1 -= 1;
                    break;
            }
            player1LineUpdate();
            if(stripesPlayer1 > 0){
                showWinner(player1Name.getText() + " wins this round!", "you got rid of " + linesToRemove + " line(s)");
            }else{
                showGameWinner(player1Name.getText() + " won the game!", "you swiped away all of your stripes first.");
            }

        }else{
            switch (player2Val){
                // three monkeys
                case 10000:
                    if(stripesPlayer2 >= maxStripes){
                        // you lose
                        showGameWinner(player1Name.getText() + "won!", player2Name.getText() + " threw three monkeys before swiping away one of his lines and that makes him the big loser of the game.");
                    }else{
                        //you win
                        linesToRemove = "all";
                        stripesPlayer2 = 0;
                    }
                    break;
                //Soixante neuf
                case 9000:
                    linesToRemove = "three";
                    stripesPlayer2 -= 3;
                    break;
                //sand
                case 8000:
                case 7000:
                case 6000:
                case 5000:
                case 4000:
                    linesToRemove = "two";
                    stripesPlayer2 -= 2;
                    break;
                default:
                    //remove 1 stripe
                    linesToRemove = "one";
                    stripesPlayer2 -= 1;
                    break;
            }
            player2LineUpdate();
            if(stripesPlayer2 > 0){
                showWinner(player2Name.getText() + " wins this round!", "you got rid of " + linesToRemove + " line(s)");
            }else{
                showGameWinner(player2Name.getText() + " won the game!", "you swiped away all of your stripes first.");
            }
        }

    }

    // popup message for winner
    public void showWinner (String winner, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(winner);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // onclick close alertDialog
                dialog.cancel();
            }
        });
        builder.show();
    }

    //popup message for deciding complete game winner
    public void showGameWinner(String winner, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(winner);
        builder.setMessage(message);
        builder.setPositiveButton("start over", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // onclick close alertDialog
                reset();
                dialog.cancel();
            }
        });
        builder.show();
    }

    //reset game to play again
    public void reset() {
        Intent LoginIntent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(LoginIntent);
    }

    // visual update of lines player1
    public void player1LineUpdate(){
        switch(stripesPlayer1){
            case 8:
                player1Lines.setBackgroundResource(R.drawable.eightline);
                break;
            case 7:
                player1Lines.setBackgroundResource(R.drawable.sevenline);
                break;
            case 6:
                player1Lines.setBackgroundResource(R.drawable.sixline);
                break;
            case 5:
                player1Lines.setBackgroundResource(R.drawable.fiveline);
                break;
            case 4:
                player1Lines.setBackgroundResource(R.drawable.fourline);
                break;
            case 3:
                player1Lines.setBackgroundResource(R.drawable.threeline);
                break;
            case 2:
                player1Lines.setBackgroundResource(R.drawable.twoline);
                break;
            case 1:
                player1Lines.setBackgroundResource(R.drawable.oneline);
                break;
        }
    }

    // visual update of lines player1
    public void player2LineUpdate(){
        switch(stripesPlayer2){
            case 8:
                player2Lines.setBackgroundResource(R.drawable.eightline);
                break;
            case 7:
                player2Lines.setBackgroundResource(R.drawable.sevenline);
                break;
            case 6:
                player2Lines.setBackgroundResource(R.drawable.sixline);
                break;
            case 5:
                player2Lines.setBackgroundResource(R.drawable.fiveline);
                break;
            case 4:
                player2Lines.setBackgroundResource(R.drawable.fourline);
                break;
            case 3:
                player2Lines.setBackgroundResource(R.drawable.threeline);
                break;
            case 2:
                player2Lines.setBackgroundResource(R.drawable.twoline);
                break;
            case 1:
                player2Lines.setBackgroundResource(R.drawable.oneline);
                break;
        }
    }
}

