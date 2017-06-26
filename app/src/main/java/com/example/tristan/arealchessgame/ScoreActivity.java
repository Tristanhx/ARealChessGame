package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tristan.arealchessgame.chess_engine.board.Board;


public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle extras = getIntent().getExtras();
        String winner = extras.getString("winner");
        Toast.makeText(this, winner + " CheckMate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        GameController.instance = null;
        Board.instance = null;
        finish();
    }
}

