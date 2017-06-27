package com.example.tristan.arealchessgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ScoreObject scoreObject;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaticApplicationContext.context = this.getApplicationContext();

        sharedPreferences = this.getSharedPreferences(getString(R.string.score_key), Context.MODE_PRIVATE);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int whiteScore = sharedPref.getInt(getString(R.string.white_score), 0);
        int blackScore = sharedPref.getInt(getString(R.string.black_score), 0);
        scoreObject = ScoreObject.getInstance(whiteScore, blackScore);

        sharedPreferences = this.getSharedPreferences(getString(R.string.score_key), Context.MODE_PRIVATE);

        Button testButton = (Button) findViewById(R.id.play_button);
        Button scoreButton = (Button) findViewById(R.id.score_button);


    }

    public void testBoard(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);


    }

    public void scoreBoard(View view){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public void settingsScreen(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
