package com.example.tristan.arealchessgame.chess_engine.player.computer_player;

import com.example.tristan.arealchessgame.GameChanger;
import com.example.tristan.arealchessgame.StaticApplicationContext;
import com.example.tristan.arealchessgame.chess_engine.board.Board;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tristan on 22/06/2017.
 */

public class TurnWatcher implements Observer {


    @Override
    public void update(final Observable o, final Object arg) {
        if (GameChanger.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())
                && Board.getInstance().getCurrentPlayer().checkMate() && Board.getInstance().getCurrentPlayer().staleMate()){
            final Thinker thinker = new Thinker();
            thinker.execute();
        }
    }
}
