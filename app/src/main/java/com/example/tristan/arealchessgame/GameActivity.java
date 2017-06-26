package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tristan.arealchessgame.chess_engine.board.Board;

public class GameActivity extends AppCompatActivity {

    private static boolean visibility;
    Board board;
    BoardGridView boardGridView;
    GameChanger gameChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setVisible();

        board = Board.getInstance();

        setContentView(R.layout.activity_game);

        boardGridView = (BoardGridView) findViewById(R.id.chessboard);
        TextView counter = (TextView) findViewById(R.id.counter);

        gameChanger = GameChanger.getInstance(boardGridView, counter);
        Log.d("boardstring", board.toString());
    }

    public void resetBoard(View view){
        Board.instance = Board.createDefaultBoard();
        GameChanger.getInstance().isFirstMove = true;
        boardGridView.invalidate();
    }

    public void forfeitPlayer(View view) {
        if(!Board.getInstance().getCurrentPlayer().checkInCheck()) {
            Board.getInstance().getCurrentPlayer().setForfeited();
        }
    }

    public static boolean visibility(){
        return visibility;
    }

    public static void setVisible(){
        visibility = true;
    }
    public static void setInvisible(){
        visibility = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        setVisible();
    }
    @Override
    protected void onPause(){
        super.onPause();
        setInvisible();
    }

    @Override
    public void onBackPressed(){
        GameChanger.currentPlayer = null;
        finish();
    }
}
