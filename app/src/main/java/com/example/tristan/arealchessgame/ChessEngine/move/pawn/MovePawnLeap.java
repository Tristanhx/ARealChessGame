package com.example.tristan.arealchessgame.ChessEngine.move.pawn;

import android.util.Log;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Pawn;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 16/06/2017.
 */

public class MovePawnLeap extends Move {
    public MovePawnLeap(Board board, Piece piece, int xDestination, int yDestination) {
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
        final Pawn pawn = (Pawn) this.piece.movePiece(this);
        builder.setPiece(pawn);
        //set this piece as the piece that can me taken en passant next move
        builder.setEnPassantPiece(pawn);
        Log.d("en passant", "This " + pawn.getAlliance().toString() + " pawn has been set as en passant");

        // Set Enemy Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
            builder.setPiece(piece);
        }

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
//        builder.setChosenMove(this);
        return builder.build();
    }
}
