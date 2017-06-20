package com.example.tristan.arealchessgame.ChessEngine.player.computerPlayer;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;

/**
 * Created by trist on 6/20/2017.
 */

public interface Evaluator {

    int evaluate(Board board, int depth);
}
