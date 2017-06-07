package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tristan.arealchessgame.GUI.BoardGridView;
import com.example.tristan.arealchessgame.GUI.CustomGridViewAdapter;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    CustomGridViewAdapter boardAdapter;
    ArrayList<String> piecesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setUpWhite();

        BoardGridView chessBoard = (BoardGridView) findViewById(R.id.chessboard);
        boardAdapter = new CustomGridViewAdapter(this, R.layout.piece_image, piecesList);
        chessBoard.setAdapter(boardAdapter);

    }

    public void setUpWhite(){
        for (int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if (j == 1 || j == 6){
                    piecesList.add("Pawn");
                }
                else{
                    piecesList.add("Empty");
                }

            }
        }
        Log.d("piecesList", piecesList.toString());
    }

    public void setUpBlack(){
        Log.d("", "");
    }
}
