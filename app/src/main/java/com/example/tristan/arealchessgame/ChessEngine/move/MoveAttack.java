package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class MoveAttack extends Move {

    Piece attackedPiece;

    public MoveAttack(Board board, Piece piece, Piece attackedPiece, int xDestination, int yDestination) {
        super(board, piece, xDestination, yDestination);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public Board execute() {
        return null;
    }
}
