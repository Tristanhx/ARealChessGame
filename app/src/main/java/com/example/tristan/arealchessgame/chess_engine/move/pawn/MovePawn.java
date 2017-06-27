package com.example.tristan.arealchessgame.chess_engine.move.pawn;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.pieces.Queen;

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
        if ((getYDestination() == 0 && piece.getAlliance().isWhite())||
                (getYDestination() == 7 && piece.getAlliance().isBlack())){
            builder.setPiece(new Queen(getXDestination(), getYDestination(), piece.getAlliance(), true));
        }
        else {
            builder.setPiece(this.piece.movePiece(this));
        }

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
