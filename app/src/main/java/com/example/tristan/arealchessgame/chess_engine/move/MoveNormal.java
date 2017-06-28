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
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        placeOwnPieces(builder);

        // Set Enemy Player pieces on new Board
        placeEnemyPieces(builder);

        // Move Piece
        builder.setPiece(this.piece.movePiece(this));

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
        return builder.build();
    }

}
