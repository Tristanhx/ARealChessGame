package com.example.tristan.arealchessgame.chess_engine.player.computer_player;

import com.example.tristan.arealchessgame.chess_engine.board.Board;

/**
 * Created by trist on 6/20/2017.
 */

public interface Evaluator {

    int evaluate(Board board, int depth);
}
