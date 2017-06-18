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

    @Override
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getPlayerPieces()) {
            if (!this.piece.equals(piece)) {
                builder.setPiece(piece);
            }

        }
        // Move Piece
        builder.setPiece(this.piece.movePiece(this));

        // Set Enemy Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
            if(piece != attackedPiece) {
                builder.setPiece(piece);
            }
        }
        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
//        builder.setChosenMove(this);
        return builder.build();
    }
}
