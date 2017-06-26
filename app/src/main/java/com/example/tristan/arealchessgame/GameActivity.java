package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.gui.BackGroundView;
import com.example.tristan.arealchessgame.gui.BoardGridView;

public class GameActivity extends AppCompatActivity {

    private static boolean visibility;
    Board board;
    BoardGridView boardGridView;
    BackGroundView backGroundView;
    GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setVisible();

        board = Board.getInstance();

        setContentView(R.layout.activity_game);

        backGroundView = (BackGroundView) findViewById(R.id.background);
        boardGridView = (BoardGridView) findViewById(R.id.chessboard);

        gameController = GameController.getInstance(boardGridView, backGroundView);
        Log.d("boardstring", board.toString());
    }

    public void resetBoard(View view){
        Board.instance = Board.createDefaultBoard();
        GameController.getInstance().isFirstMove = true;
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
        if (GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())
                || GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer().getOpponent())) {
            GameController.instance = null;
            Board.instance = null;
            finish();
        }
        else{
            super.onBackPressed();
        }
    }
}
