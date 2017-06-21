package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tristan.arealchessgame.chess_engine.board.Board;

public class GameActivity extends AppCompatActivity {

    Board board;
    BoardGridView chessBoardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        board = Board.getInstance();

        setContentView(R.layout.activity_game);

        chessBoardView = (BoardGridView) findViewById(R.id.chessboard);

        Log.d("boardstring", board.toString());
    }

    public void resetBoard(View view){
        Board.instance = Board.createDefaultBoard();
        chessBoardView.invalidate();
    }
}
