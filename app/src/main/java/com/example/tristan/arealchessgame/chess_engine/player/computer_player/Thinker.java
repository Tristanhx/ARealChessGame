package com.example.tristan.arealchessgame.chess_engine.player.computer_player;

import android.os.AsyncTask;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.google.common.base.Strings;

/**
 * Created by Tristan on 22/06/2017.
 */

class Thinker extends AsyncTask<Move, String, Move> {

    public Thinker(){

    }

    @Override
    protected Move doInBackground(Move... params) {

        final Strategy miniMax = new MiniMax(2);
        final Move bestMove = miniMax.execute(Board.getInstance());

        return bestMove;
    }


    @Override
    public void onPostExecute(Move move){

    }
}
