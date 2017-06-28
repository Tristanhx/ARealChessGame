package com.example.tristan.arealchessgame.chess_engine.move.pawn;

import android.util.Log;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.pieces.Pawn;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 16/06/2017.
 */

public class MovePawnLeap extends Move {
    public MovePawnLeap(Board board, Piece piece, int xDestination, int yDestination) {
        super(board, piece, xDestination, yDestination);
    }

    @Override
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        placeOwnPieces(builder);

        // Set Enemy Player pieces on new Board
        placeEnemyPieces(builder);

        // Move Piece
        final Pawn pawn = (Pawn) this.piece.movePiece(this);
        builder.setPiece(pawn);
        //set this piece as the piece that can me taken en passant next move
        builder.setEnPassantPiece(pawn);
        Log.d("en passant", "This " + pawn.getAlliance().toString() + " pawn has been set as en passant");

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
        return builder.build();
    }
}
