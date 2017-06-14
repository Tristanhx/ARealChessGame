package com.example.tristan.arealchessgame.ChessEngine.player;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

/**
 * Created by Tristan on 14/06/2017.
 */

public class PlayerMove {

    private final Board nextBoard;
    private final Move move;

    public PlayerMove(final Board board, final Move move){
        this.nextBoard = board;
        this.move = move;
    }
}
