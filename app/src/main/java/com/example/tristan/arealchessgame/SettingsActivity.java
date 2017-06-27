package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tristan.arealchessgame.chess_engine.board.Board;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
//
//    @Override
//    public void onBackPressed(){
//        GameController.instance = null;
//        Board.instance = null;
//        finish();
//    }
}
