package com.example.tristan.arealchessgame.ChessEngine.move.pawn;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveAttack;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 16/06/2017.
 */

public class MovePawnAttack extends MoveAttack {
    public MovePawnAttack(Board board, Piece piece, Piece attackedPiece, int xDestination, int yDestination) {
        super(board, piece, attackedPiece, xDestination, yDestination);
    }
}
