package com.example.tristan.arealchessgame.chess_engine.player.computer_player;

import android.os.AsyncTask;
import android.view.View;

import com.example.tristan.arealchessgame.BoardGridView;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.player.AlternateBoard;
import com.google.common.base.Strings;

import java.util.concurrent.ExecutionException;

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

        try {
            final Move bestMove = get();
            final AlternateBoard newBoard = Board.getInstance().getCurrentPlayer().makeMove(bestMove);
            Board.instance = newBoard.getBoard();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
