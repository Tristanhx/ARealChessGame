package com.example.tristan.arealchessgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.gui.BackGroundView;
import com.example.tristan.arealchessgame.gui.BoardGridView;

public class GameActivity extends AppCompatActivity {

    Board board;
    BoardGridView boardGridView;
    BackGroundView backGroundView;
    GameController gameController;
    ScoreObject scoreObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        board = Board.getInstance();

        scoreObject = ScoreObject.getInstance();

        setContentView(R.layout.activity_game);

        backGroundView = (BackGroundView) findViewById(R.id.background);
        boardGridView = (BoardGridView) findViewById(R.id.chessboard);

        gameController = GameController.getInstance(boardGridView, backGroundView);
        gameController.setAllowed(true);
        Log.d("boardstring", board.toString());
    }

    public void resetBoard(View view){
        Board.instance = Board.createDefaultBoard();
        GameController.getInstance().isFirstMove = true;
        boardGridView.invalidate();
        backGroundView.invalidate();
    }

    public void forfeitPlayer(View view) {
        if(!Board.getInstance().getCurrentPlayer().checkInCheck()) {
            Board.getInstance().getCurrentPlayer().setForfeited();
        }
    }

    @Override
    protected void onDestroy(){
        gameController.setAllowed(false);
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.white_score), scoreObject.getWhitePlayerScore());
        editor.putInt(getString(R.string.black_score), scoreObject.getBlackPlayerScore());
        editor.apply();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        if (GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())
                && GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer().getOpponent())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Leave Game?")
                    .setMessage("Computer vs Computer games are terminated to save resources")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int which){
                        GameController.instance = null;
                            Board.instance = null;
                            finish();
                        }
                    }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialogInterface, int which){
                            // continue
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
        }
        else{
            super.onBackPressed();
        }
    }
}
