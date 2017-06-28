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
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        placeOwnPieces(builder);

        // Set Enemy Player pieces on new Board
        placeEnemyPieces(builder);

        // Move Piece
        if ((getYDestination() == 0 && piece.getAlliance().isWhite())||
                (getYDestination() == 7 && piece.getAlliance().isBlack())){
            builder.setPiece(new Queen(getXDestination(), getYDestination(), piece.getAlliance(), true));
        }
        else {
            builder.setPiece(this.piece.movePiece(this));
        }

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
//        builder.setChosenMove(this);
        return builder.build();
    }
}
