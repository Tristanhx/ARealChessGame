package com.example.tristan.arealchessgame;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tristan.arealchessgame.chess_engine.Setup;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.player.AlternateBoard;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.MiniMax;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.Strategy;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

/**
 * Created by trist on 6/21/2017.
 */

public class GameChanger extends Observable {

    private static GameChanger instance = null;
    Setup setup;

    public static Player currentPlayer = null;

    private Observer AI;



    public static GameChanger getInstance(){
        if (instance == null){
            instance = new GameChanger(StaticApplicationContext.context);
        }
        return instance;
    }


    protected GameChanger(Context context){
        this.setup = new Setup(context);
    }

    public Setup getSetup(){
        return this.setup;
    }

    private void setupUpdate(Setup setup){
        setChanged();
        notifyObservers(setup);
    }

    public void moveUpdate(final Type type){
        setChanged();
        notifyObservers(type);
    }

    public enum Type {
        HUMAN,
        COMPUTER
    }

    public class TurnWatcher implements Observer {


        @Override
        public void update(final Observable o, final Object arg) {
            if (GameChanger.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())
                    && !Board.getInstance().getCurrentPlayer().checkMate() && !Board.getInstance().getCurrentPlayer().staleMate()){
                final Thinker thinker = new Thinker();
                thinker.execute();
            }
        }
    }

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
}
