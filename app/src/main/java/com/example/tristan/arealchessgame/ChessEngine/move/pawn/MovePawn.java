package com.example.tristan.arealchessgame.ChessEngine.move.pawn;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 16/06/2017.
 */

public class MovePawn extends Move {

    public MovePawn(Board board, Piece piece, int xDestination, int yDestination) {
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
