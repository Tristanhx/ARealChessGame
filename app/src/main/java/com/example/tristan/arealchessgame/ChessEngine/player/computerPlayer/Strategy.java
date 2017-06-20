package com.example.tristan.arealchessgame.ChessEngine.player.computerPlayer;


import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;

/**
 * Created by trist on 6/20/2017.
 */

public interface Strategy {
    Move execute(Board board, int depth);
}
