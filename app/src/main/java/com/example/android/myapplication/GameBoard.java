package com.example.android.myapplication;

/**
 * Created by TOSHIBA on 4/10/2018.
 */

public class GameBoard {

    //Declare the global variables
    private String[][] Board = new String[3][3];

    //Initialize the Game board filled with empty strings

    GameBoard() {

//Initiate the game board with blanks
        for (int i = 0; i < 3; i++) {


            for (int j = 0; j < 3; j++) {
                Board[i][j] = "";
            }


        }


    }
    //Clears the game board by looping through each row and column and puts the empty string
    public void clear() {
        for (int i = 0; i < 3; i++) {


            for (int j = 0; j < 3; j++) {


                Board[i][j] = "";


            }


        }


    }
    //getter that returns the virtual board
    public String[][] getBoard() {
        return Board;
    }

    //Checks to make sure a mark doesn't already exist before placing the mark.
    public void placeMark(int x, int y, String mark) {
        if (Board[x][y] == "")

        {
            Board[x][y] = mark;
        }
    }


    //Determines if there is a winner or not checks each diagonal then loops through each row/column
    public boolean Winner() {
// Check Diagonals
        if (Board[0][0] == Board[1][1] && Board[0][0] == Board[2][2] && Board[0][0] != "")
        {
            return true;
        }
        if (Board[2][0] == Board[1][1] && Board[2][0] == Board[0][2] && Board[2][0] != "")
        {
            return true;
        }

        for (int i = 0; i < 3; i++) {
// Check Rows
            if (Board[i][0] == Board[i][1] && Board[i][1] == Board[i][2] && Board[i][0] != "") {
                return true;
            }
// Check Columns
            if (Board[0][i] == Board[1][i] && Board[1][i] == Board[2][i] && Board[0][i] != "") {
                return true;
            }

        }

        return false;
    }
}
