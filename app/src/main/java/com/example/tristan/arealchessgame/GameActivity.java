package com.example.tristan.arealchessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

import com.example.tristan.arealchessgame.GUI.BoardGridView;
import com.example.tristan.arealchessgame.GUI.CustomGridViewAdapter;
import com.example.tristan.arealchessgame.GUI.TileView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    CustomGridViewAdapter boardAdapter;
    ArrayList<String> piecesList = new ArrayList<>();
    GridLayout boardLayout;
    final List<TileView> tileViews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        boardLayout = (GridLayout) findViewById(R.id.boardLayout);
//        for (int i = 0 ; i < 64 ; i++){
//            final TileView tileView = new TileView(getApplicationContext(), i);
//            tileViews.add(tileView);
//            boardLayout.addView(tileView);
//        }

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
