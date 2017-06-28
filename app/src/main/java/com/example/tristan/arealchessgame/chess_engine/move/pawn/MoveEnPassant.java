package com.example.tristan.arealchessgame.chess_engine.move.pawn;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 16/06/2017.
 */

public class MoveEnPassant extends MovePawnAttack {
    public MoveEnPassant(Board board, Piece piece, Piece attackedPiece, int xDestination, int yDestination) {
        super(board, piece, attackedPiece, xDestination, yDestination);
    }

    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        placeOwnPieces(builder);

        // Set Enemy Player pieces on new Board
        placeAttackedEnemyPieces(builder, attackedPiece);

        // Move Piece
        builder.setPiece(this.piece.movePiece(this));

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        return builder.build();
    }
}
