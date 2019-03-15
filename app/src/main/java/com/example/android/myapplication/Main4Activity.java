package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private GameBoard board = null;
    private int x = 0, y = 0 , games=0;
    private String playerA ="X" ;
    private String playerB ="O";
    private boolean isOver = false;
    private String currentPlayer = null;
    private int scoreX = 0;
    private int score0 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        board = new GameBoard();
    }

    //Action when reset is clicked which clears the screen and the virtual game board
    public void Reset(View v)
    {
        clear();
    }




    //Action for when a cell is clicked. Determines which cell has been clicked and passed that
// information on the the virtual game board.
    public void buttonClicked(View v) {

        if (currentPlayer == playerA) {
//Get the id of the clicked object and assign it to a Textview variable
            Button cell = (Button) findViewById(v.getId());
//Check the content and make sure the cell is empty and that the game isn't over
            String content = (String) cell.getText();
            if (content == "" && !isOver) {
//Find the X Y location values of the particular cell that was clicked
                if (cell.getId() == R.id.cell11) {
                    x = 0;
                    y = 0;
                }
                if (cell.getId() == R.id.cell12) {
                    x = 0;
                    y = 1;
                }
                if (cell.getId() == R.id.cell13) {
                    x = 0;
                    y = 2;
                }
                if (cell.getId() == R.id.cell21) {
                    x = 1;
                    y = 0;
                }
                if (cell.getId() == R.id.cell22) {
                    x = 1;
                    y = 1;
                }
                if (cell.getId() == R.id.cell23) {
                    x = 1;
                    y = 2;
                }
                if (cell.getId() == R.id.cell31) {
                    x = 2;
                    y = 0;
                }
                if (cell.getId() == R.id.cell32) {
                    x = 2;
                    y = 1;
                }
                if (cell.getId() == R.id.cell32) {
                    x = 2;
                    y = 2;
                }
                //Place the player's mark on the specific X Y location on both the virtual and displayed board
                board.placeMark(x, y, playerA);
                cell.setText(playerA);

//Check to see if the game is over so as to display the score
                isOver = checkEnd(playerA);


//if the game game is over get the AI's move
                if (!isOver) {
                    changePlayer(playerA);
                } else if (isOver) {
                    calculateScore(playerA);
                }
            }
        } else if (currentPlayer == playerB) {
//Get the id of the clicked object and assign it to a Textview variable
            Button cell = (Button) findViewById(v.getId());
//Check the content and make sure the cell is empty and that the game isn't over
            String content = (String) cell.getText();
            if (content == "" && !isOver) {
//Find the X Y location values of the particular cell that was clicked
                if (cell.getId() == R.id.cell11) {
                    x = 0;
                    y = 0;
                }
                if (cell.getId() == R.id.cell12) {
                    x = 0;
                    y = 1;
                }
                if (cell.getId() == R.id.cell13) {
                    x = 0;
                    y = 2;
                }
                if (cell.getId() == R.id.cell21) {
                    x = 1;
                    y = 0;
                }
                if (cell.getId() == R.id.cell22) {
                    x = 1;
                    y = 1;
                }
                if (cell.getId() == R.id.cell23) {
                    x = 1;
                    y = 2;
                }
                if (cell.getId() == R.id.cell31) {
                    x = 2;
                    y = 0;
                }
                if (cell.getId() == R.id.cell32) {
                    x = 2;
                    y = 1;
                }
                if (cell.getId() == R.id.cell32) {
                    x = 2;
                    y = 2;
                }
                //Place the player's mark on the specific X Y location on both the virtual and displayed board
                board.placeMark(x, y, playerB);
                cell.setText(playerB);

//Check to see if the game is over so as to display the score
                isOver = checkEnd(playerB);


//if the game game is over get the AI's move
                if (!isOver) {
                    changePlayer(playerB);
                } else if (isOver) {
                    calculateScore(playerB);
                }
            }
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "One of you should choose to be \n either player X or player O";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 320, 740);
            toast.show();

        }
    }





    //Even for when the user changes between going first and going second
    public void checkboxChecked(View view) {
        CheckBox one = (CheckBox) findViewById(R.id.checkboxX);
        CheckBox two = (CheckBox) findViewById(R.id.checkboxO);

        boolean checked = (one).isChecked();//This is for the player X
        boolean checked1 = (two).isChecked();//This is for the player O

        if(checked)
        {
            currentPlayer = null;
           currentPlayer=playerA;
            clear();
        }
        if(checked1)
        {
            currentPlayer = null;
            currentPlayer=playerB;
            clear();
        }


    }
    public void changePlayer(String player)
    {
        if(player == playerA)
        {
            currentPlayer = null;
            currentPlayer = playerB;
        }
        if(player == playerB)
        {
            currentPlayer = null;
            currentPlayer = playerA;
        }
    }
    public void unckecking()
    {
        CheckBox one = (CheckBox) findViewById(R.id.checkboxX);
        CheckBox two = (CheckBox) findViewById(R.id.checkboxO);

        boolean checked = (one).isChecked();
        boolean checked1 = (two).isChecked();
        if(checked)
        {
            one.toggle();
        }
        if(checked1)
        {
            two.toggle();
        }
    }

    //Checks to see if the game has ended provided with the last player to make a move
    private boolean checkEnd(String player) {
/* Checks the virtual board for a winner and then display the score */
        if (board.Winner())
        {
            return true;
        }

//If not win then the game is still on
        return false;
    }

    public void calculateScore(String player)
    {
        String message;
        if( player== playerA )
        {
            scoreX = scoreX + 10;
            TextView marks = (TextView) findViewById(R.id.PlayerX);
            message = Integer.toString(scoreX);
            marks.setText("Player X : "+message);

            Context context = getApplicationContext();
            CharSequence text = "Player X has won. \nThe player now has" + scoreX + "points";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 320, 740);
            toast.show();

        }
        else
        {
            score0=score0 + 10;
            TextView marks = (TextView) findViewById(R.id.Player0);
            message = Integer.toString(score0);
            marks.setText("Player 0 : "+message);

            Context context = getApplicationContext();
            CharSequence text = "Player 0 has won. \nThe player now has" + score0 + "points";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 320, 740);
            toast.show();

        }
        games = games +1;

        TextView marks = (TextView) findViewById(R.id.cell60);
        marks.setText("Games Played " + games);
    }

    //Clears the game Board
    private void clear()
    {
        Button cell;
        cell = (Button) findViewById(R.id.cell11);
        cell.setText("");
        Button cell1;
        cell1 = (Button) findViewById(R.id.cell12);
        cell1.setText("");
        Button cell2;
        cell2 = (Button) findViewById(R.id.cell13);
        cell2.setText("");
        Button cell3;
        cell3 = (Button) findViewById(R.id.cell21);
        cell3.setText("");
        Button cell4;
        cell4 = (Button) findViewById(R.id.cell22);
        cell4.setText("");
        Button cell5;
        cell5 = (Button) findViewById(R.id.cell23);
        cell5.setText("");
        Button cell6;
        cell6 = (Button) findViewById(R.id.cell31);
        cell6.setText("");
        Button cell7;
        cell7 = (Button) findViewById(R.id.cell32);
        cell7.setText("");
        Button cell8;
        cell8 = (Button) findViewById(R.id.cell33);
        cell8.setText("");


//Reset the game state and clear the virtual board
        isOver = false;
        board.clear();
    }

    public void gridFive(View view)
    {
        Intent i = new Intent(this,Main5Activity.class);
        startActivity(i);
    }


    public void Exit(View view)
    {
        finish();
    }

    public void newGame(View v)
    {
        clear();
        unckecking();
        TextView one = (TextView)findViewById(R.id.PlayerX);
        one.setText("Player X");
        TextView two =(TextView)findViewById(R.id.Player0);
        two.setText("Player 0");
        score0=0;
        scoreX=0;
        games=0;
        currentPlayer = null;
        TextView three= (TextView) findViewById(R.id.cell60);
        three.setText("Games Played");

        Context context = getApplicationContext();
        CharSequence text = "This is a new Game session";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 320, 740);
        toast.show();
    }

}
