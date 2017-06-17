package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 15/06/2017.
 */

public class NoMove extends Move {
    NoMove() {
        super(null, null, -1, -1);
    }

    @Override
    public boolean isAttackMove() {
        return false;
    }

    @Override
    public Piece getAttackedPiece() {
        return null;
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
