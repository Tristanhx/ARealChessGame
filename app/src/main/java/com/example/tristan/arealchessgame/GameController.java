package com.example.tristan.arealchessgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.Setup;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.board.AlternateBoard;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.MiniMax;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.Strategy;
import com.example.tristan.arealchessgame.gui.BackGroundView;
import com.example.tristan.arealchessgame.gui.BoardGridView;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

/**
 * Created by trist on 6/21/2017.
 */

public class GameController extends Observable implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static GameController instance = null;
    private Setup setup;
    public static BoardGridView boardGridView;
    public static BackGroundView backGroundView;
    public Boolean isFirstMove = true;
    private Boolean allowed;

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StaticApplicationContext.context);

    public static Player currentPlayer = null;

    public static GameController getInstance(BoardGridView BGV, BackGroundView bgView){
        if (instance == null){
            instance = new GameController(StaticApplicationContext.context);
        }
        boardGridView = BGV;
        backGroundView = bgView;
        return instance;
    }

    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController(StaticApplicationContext.context);
        }
        return instance;
    }

    protected GameController(Context context){
        this.setup = new Setup(context);
        preferences.registerOnSharedPreferenceChangeListener(this);
        this.addObserver(new TurnWatcher());
        this.allowed = true;
    }

    public void notFirstMove(){
        this.isFirstMove = false;
    }

    public void setAllowed(Boolean allowed){
        this.allowed = allowed;
    }

    public Boolean getAllowed(){
        return this.allowed;
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.setup = new Setup(StaticApplicationContext.context);
        setupUpdate(setup);
    }

    public enum Type {
        HUMAN,
        COMPUTER
    }

    private static class TurnWatcher implements Observer {


        @Override
        public void update(final Observable o, final Object arg) {
            if (GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())
                    && !Board.getInstance().getCurrentPlayer().checkMate() &&
                    !Board.getInstance().getCurrentPlayer().staleMate()){
                final Thinker thinker = new Thinker();
                thinker.execute();
            }
        }
    }

    private static class Thinker extends AsyncTask<Move, String, Move> {


        Thinker(){

        }

        @Override
        protected Move doInBackground(Move... params) {

            final Strategy miniMax = new MiniMax(GameController.getInstance().getSetup().getDepth());
            final Move bestMove = miniMax.execute(Board.getInstance());

            return bestMove;
        }

        @Override
        public void onPostExecute(Move move){

            if (Board.getInstance().endGame() == Alliance.NONE && !GameController.getInstance().isFirstMove) {
                try {
                    final Move bestMove = get();
                    final AlternateBoard newBoard = Board.getInstance().getCurrentPlayer().makeMove(bestMove);
                    Board.instance = newBoard.getBoard();
                    if (newBoard.getMoveWas().isExecuted()) {
                        Piece movedPiece = bestMove.getPiece();
                        Log.d("Computer", Board.getInstance().getCurrentPlayer().getOpponent().getAlliance().isBlack()
                                ? "Black made a move " + movedPiece.toString() + " " +
                                bestMove.toString() : "White made a move " + movedPiece.toString()
                                + " " + bestMove.toString());
                    }
                    Thread.sleep(500);
                    GameController.getInstance().notFirstMove();
                    if (GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())) {
                        GameController.getInstance().moveUpdate(Type.COMPUTER);
                    }
                    backGroundView.invalidate();
                    boardGridView.invalidate();
                } catch (InterruptedException i) {
                    i.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

//            do {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }while(!GameController.getInstance().getAllowed());

            }
        }
    }
}
