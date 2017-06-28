package com.example.tristan.arealchessgame.chess_engine.move;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 15/06/2017.
 */

public class NoMove extends Move {
    NoMove() {
        super(null, null, -1, -1);
    }


    @Override
    public int hashCode(){
        return 12345;
    }

    @Override
    public Board execute() {
        throw new RuntimeException("NO MOVE");
    }
}
