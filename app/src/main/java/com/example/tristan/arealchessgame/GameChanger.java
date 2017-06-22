package com.example.tristan.arealchessgame;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.computer_player.MiniMax;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by trist on 6/21/2017.
 */

public class GameChanger extends Observable {

    public static Player currentPlayer = null;
    private Observer AI;
}
