package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tristan.arealchessgame.chess_engine.board.Board;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Board board = Board.createDefaultBoard();

        setContentView(R.layout.activity_game);

        Log.d("boardstring", board.toString());
    }
}
