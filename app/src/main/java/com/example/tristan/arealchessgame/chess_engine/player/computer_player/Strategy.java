package com.example.tristan.arealchessgame.chess_engine.player.computer_player;


import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;

/**
 * Created by trist on 6/20/2017.
 */

public interface Strategy {
    Move execute(Board board);
}
