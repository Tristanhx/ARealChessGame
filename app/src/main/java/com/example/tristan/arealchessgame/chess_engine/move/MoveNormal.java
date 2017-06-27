package com.example.tristan.arealchessgame.chess_engine.move;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

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

    @Override
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getPlayerPieces()){
            if (!this.piece.equals(piece)){
                builder.setPiece(piece);
            }

        }
        // Move Piece
        builder.setPiece(this.piece.movePiece(this));

        // Set Enemy Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
            builder.setPiece(piece);
        }

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
//        builder.setChosenMove(this);
        return builder.build();
    }

}
