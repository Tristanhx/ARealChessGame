package com.example.tristan.arealchessgame.chess_engine.board;

import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveWas;

/**
 * Created by Tristan on 14/06/2017.
 */

public class AlternateBoard {

    private final Board nextBoard;
    private final Move move;
    private final MoveWas moveWas;

    public AlternateBoard(final Board board, final Move move, final MoveWas moveWas){
        this.nextBoard = board;
        this.move = move;
        this.moveWas = moveWas;
    }

    public MoveWas getMoveWas(){
        return this.moveWas;
    }

    public Board getBoard() {
        return nextBoard;
    }
}
