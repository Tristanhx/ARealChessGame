package com.example.tristan.arealchessgame.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tristan.arealchessgame.R;
import com.example.tristan.arealchessgame.ScoreObject;
import com.example.tristan.arealchessgame.chess_engine.board.Board;


public class ScoreActivity extends AppCompatActivity {

    TextView whiteScore;
    TextView blackScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        whiteScore = (TextView) findViewById(R.id.whitescore);
        blackScore = (TextView) findViewById(R.id.blackscore);

        whiteScore.setText(ScoreObject.getInstance().getWhitePlayerScore());
        blackScore.setText(ScoreObject.getInstance().getBlackPlayerScore());

    }
}

