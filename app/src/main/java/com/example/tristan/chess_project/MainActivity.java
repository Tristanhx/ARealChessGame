package com.example.tristan.chess_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
