package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class MoveNormal extends Move {

    public MoveNormal(Board board, Piece piece, int xDestination, int yDestination) {
        super(board, piece, xDestination, yDestination);
    }

    @Override
    public boolean isAttackMove() {
        return false;
    }

    @Override
    public Piece getAttackedPiece() {
        return null;
    }

}
