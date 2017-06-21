package com.example.tristan.arealchessgame;

import com.example.tristan.arealchessgame.chess_engine.player.Player;

import java.util.Observable;

/**
 * Created by trist on 6/21/2017.
 */

public class GameChanger extends Observable {

    private Player currentPlayer;

    public GameChanger(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }


}
