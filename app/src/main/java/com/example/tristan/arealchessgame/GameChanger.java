package com.example.tristan.arealchessgame;

import android.content.Context;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.Setup;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.MiniMax;

import java.util.Observable;
import java.util.Observer;

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
}
