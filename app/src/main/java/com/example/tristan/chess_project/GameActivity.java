package com.example.tristan.chess_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tristan.chess_project.GUI.BoardGridView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BoardGridView chessBoard = (BoardGridView) findViewById(R.id.chessboard);

    }

    public void setUpWhite(){
        Log.d("", "");
    }

    public void setUpBlack(){
        Log.d("", "");
    }
}
