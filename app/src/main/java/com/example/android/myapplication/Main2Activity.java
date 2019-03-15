package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private GameBoard1 board = null;
    private int x = 0, y = 0 , games=0;
    private String mark = "X", aiMark = "O";
    private boolean isOver = false;
    private comp1 ai = null;
    private int scoreX = 0;
    private int score0 = 0;
    private boolean isChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        board = new GameBoard1();

        ai = new comp1(aiMark);
    }

    //Action when reset is clicked which clears the screen and the virtual game board
    public void Reset(View v) {
        clear();
        if (aiMark =="X")
        {
            getcomputer1(board);
        }
    }

    //Action for when a cell is clicked. Determines which cell has been clicked and passed that
// information on the the virtual game board.
    public void buttonClicked(View v) {
        if(isChecked) {
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
                if (cell.getId() == R.id.cell14) {
                    x = 0;
                    y = 3;
                }
                if (cell.getId() == R.id.cell15) {
                    x = 0;
                    y = 4;
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
                if (cell.getId() == R.id.cell24) {
                    x = 1;
                    y = 3;
                }
                if (cell.getId() == R.id.cell25) {
                    x = 1;
                    y = 4;
                }
                if (cell.getId() == R.id.cell31) {
                    x = 2;
                    y = 0;
                }
                if (cell.getId() == R.id.cell32) {
                    x = 2;
                    y = 1;
                }
                if (cell.getId() == R.id.cell33) {
                    x = 2;
                    y = 2;
                }
                if (cell.getId() == R.id.cell34) {
                    x = 2;
                    y = 3;
                }
                if (cell.getId() == R.id.cell35) {
                    x = 2;
                    y = 4;
                }
                if (cell.getId() == R.id.cell41) {
                    x = 3;
                    y = 0;
                }
                if (cell.getId() == R.id.cell42) {
                    x = 3;
                    y = 1;
                }
                if (cell.getId() == R.id.cell43) {
                    x = 3;
                    y = 2;
                }
                if (cell.getId() == R.id.cell44) {
                    x = 3;
                    y = 3;
                }
                if (cell.getId() == R.id.cell45) {
                    x = 3;
                    y = 4;
                }
                if (cell.getId() == R.id.cell51) {
                    x = 4;
                    y = 0;
                }
                if (cell.getId() == R.id.cell52) {
                    x = 4;
                    y = 1;
                }
                if (cell.getId() == R.id.cell53) {
                    x = 4;
                    y = 2;
                }
                if (cell.getId() == R.id.cell54) {
                    x = 4;
                    y = 3;
                }
                if (cell.getId() == R.id.cell55) {
                    x = 4;
                    y = 4;
                }
                //Place the player's mark on the specific X Y location on both the virtual and displayed board
                board.placeMark(x, y, mark);
                cell.setText(mark);

//Check to see if the game is over so as to display the score
                isOver = checkEnd(mark);


//if the game game is over get the AI's move
                if (!isOver) {
                    getcomputer1(board);
                } else if (isOver) {
                    calculateScore(mark);
                }
            }
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Choose to be \n either player X or O";
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

        boolean checked = (one).isChecked();
        boolean checked1 = (two).isChecked();

        if(checked)
        {
            mark = "X";
            aiMark = "O";
            clear();
            isChecked = true;
        }
        if(checked1)
        {
            mark = "O";
            aiMark = "X";
            clear();
            getcomputer1(board);
            isChecked = true;
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
        if( player=="X" )
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
        games = games+1;
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
        cell3 = (Button) findViewById(R.id.cell14);
        cell3.setText("");
        Button cell4;
        cell4 = (Button) findViewById(R.id.cell15);
        cell4.setText("");
        Button cell5;
        cell5 = (Button) findViewById(R.id.cell21);
        cell5.setText("");
        Button cell6;
        cell6 = (Button) findViewById(R.id.cell22);
        cell6.setText("");
        Button cell7;
        cell7 = (Button) findViewById(R.id.cell23);
        cell7.setText("");
        Button cell8;
        cell8 = (Button) findViewById(R.id.cell24);
        cell8.setText("");
        Button cell9;
        cell9 = (Button) findViewById(R.id.cell25);
        cell9.setText("");
        Button cell10;
        cell10 = (Button) findViewById(R.id.cell31);
        cell10.setText("");
        Button cell11;
        cell11 = (Button) findViewById(R.id.cell32);
        cell11.setText("");
        Button cell12;
        cell12 = (Button) findViewById(R.id.cell33);
        cell12.setText("");
        Button cell13;
        cell13 = (Button) findViewById(R.id.cell34);
        cell13.setText("");
        Button cell14;
        cell14 = (Button) findViewById(R.id.cell35);
        cell14.setText("");
        Button cell15;
        cell15 = (Button) findViewById(R.id.cell41);
        cell15.setText("");
        Button cell16;
        cell16 = (Button) findViewById(R.id.cell42);
        cell16.setText("");
        Button cell17;
        cell17 = (Button) findViewById(R.id.cell43);
        cell17.setText("");
        Button cell18;
        cell18 = (Button) findViewById(R.id.cell44);
        cell18.setText("");
        Button cell19;
        cell19 = (Button) findViewById(R.id.cell45);
        cell19.setText("");
        Button cell20;
        cell20 = (Button) findViewById(R.id.cell51);
        cell20.setText("");
        Button cell21;
        cell21 = (Button) findViewById(R.id.cell52);
        cell21.setText("");
        Button cell22;
        cell22 = (Button) findViewById(R.id.cell53);
        cell22.setText("");
        Button cell23;
        cell23 = (Button) findViewById(R.id.cell54);
        cell23.setText("");
        Button cell24;
        cell24 = (Button) findViewById(R.id.cell55);
        cell24.setText("");




//Reset the game state and clear the virtual board
        isOver = false;
        board.clear();
    }
    //Gets the AI's next move giving the current state of the board
    private void getcomputer1(GameBoard1 board) {
//Send the board to the AI for it to determine and return the move in an array {x,y}
        int[] move = ai.computer(board,aiMark);
        Button clicked = null;

        int i=0;
        int j=1;
        int a = move[i];
        int b = move[j];

        if(a==0 && b==0)
        {
            clicked = (Button)findViewById(R.id.cell11);
        }
        if(a==0 && b==1)
        {
            clicked = (Button)findViewById(R.id.cell12);
        }
        if(a==0 && b==2)
        {
            clicked = (Button)findViewById(R.id.cell13);
        }
        if(a==0 && b==3)
        {
            clicked =(Button)findViewById(R.id.cell14);
        }
        if(a==0 && b==4)
        {
            clicked =(Button) findViewById(R.id.cell15);
        }
        if(a==1 && b==0)
        {
            clicked = (Button)findViewById(R.id.cell21);
        }
        if(a==1 && b==1)
        {
            clicked = (Button)findViewById(R.id.cell22);
        }
        if(a==1 && b==2)
        {
            clicked = (Button)findViewById(R.id.cell23);
        }
        if(a==1 && b==3)
        {
            clicked = (Button)findViewById(R.id.cell24);
        }
        if(a==1 && b==4)
        {
            clicked = (Button)findViewById(R.id.cell25);
        }
        if(a==2 && b==0)
        {
            clicked = (Button)findViewById(R.id.cell31);
        }
        if(a==2 && b==1)
        {
            clicked = (Button)findViewById(R.id.cell32);
        }
        if(a==2 && b==2)
        {
            clicked = (Button)findViewById(R.id.cell33);
        }
        if(a==2 && b==3)
        {
            clicked =(Button)findViewById(R.id.cell34);
        }
        if(a==2 && b==4)
        {
            clicked =(Button)findViewById(R.id.cell35);
        }
        if(a==3 && b==0)
        {
            clicked =(Button)findViewById(R.id.cell41);
        }
        if(a==3 && b==1)
        {
            clicked =(Button)findViewById(R.id.cell42);
        }
        if(a==3 && b==2)
        {
            clicked =(Button)findViewById(R.id.cell43);
        }
        if(a==3 && b==3)
        {
            clicked =(Button)findViewById(R.id.cell44);
        }
        if(a==3 && b==4)
        {
            clicked =(Button)findViewById(R.id.cell45);
        }
        if(a==4 && b==0)
        {
            clicked =(Button)findViewById(R.id.cell51);
        }
        if(a==4 && b==1)
        {
            clicked =(Button)findViewById(R.id.cell52);
        }
        if(a==4 && b==2)
        {
            clicked =(Button)findViewById(R.id.cell53);
        }
        if(a==4 && b==3)
        {
            clicked =(Button)findViewById(R.id.cell54);
        }
        if(a==4 && b==4)
        {
            clicked =(Button)findViewById(R.id.cell55);
        }

        if ( clicked !=null && clicked.getText() == "")
        {


            board.placeMark(a,b, aiMark);
            clicked.setText(aiMark);
            isOver = checkEnd(aiMark);

            if(isOver)
            {
                calculateScore(aiMark);
            }

        }


    }
    public void gridThree(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
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
        isChecked = false;
        TextView three= (TextView) findViewById(R.id.cell60);
        three.setText("Games Played");

        Context context = getApplicationContext();
        CharSequence text = "This is a new Game session";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 320, 740);
        toast.show();

    }


    public void Exit(View view)
    {
        ActivityCompat.finishAffinity(Main2Activity.this);
    }
}
