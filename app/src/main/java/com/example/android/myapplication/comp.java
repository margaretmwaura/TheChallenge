package com.example.android.myapplication;

import java.util.Random;

/**
 * Created by TOSHIBA on 4/10/2018.
 */

public class comp {

    private static final Random RANDOM = new Random();
    protected String myMark;
    protected String oppMark;
    private boolean ended;
    protected  String[][] board;
    comp(String marker) {

        this.myMark = marker;
        if(myMark=="X")
        {
            this.oppMark = "O";
        }
        else
        {
            this.oppMark = "X";
        }
    }

    public int[] computer (GameBoard Gboard, String marker)
    {
        int a;
        int b;
        this.myMark = marker;
        if(myMark=="X")
        {
            this.oppMark = "O";
        }
        else
        {
            this.oppMark = "X";

        }
        this.board = Gboard.getBoard();

        do
        {
            a = RANDOM.nextInt(3);
            b =RANDOM.nextInt(3);
        } while( board[a][b] == " ");

        board[a][b] = marker;
        int [] result = new int[]{a,b};

        return result;
    }
}
