package com.example.tristan.arealchessgame.chess_engine.move.castle;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.pieces.King;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.pieces.Rook;

/**
 * Created by Tristan on 18/06/2017.
 */

public abstract class MoveCastle extends Move {
    final Rook rook;
    final int xDestinationRook;
    final int yDestinationRook;

    public MoveCastle(Board board, King king, Rook rook, int xDestinationKing, int yDestinationKing, int xDestinationRook, int yDestinationRook) {
        super(board, king, xDestinationKing, yDestinationKing);
        this.rook = rook;
        this.xDestinationRook = xDestinationRook;
        this.yDestinationRook = yDestinationRook;
    }

    @Override
    public Board execute(){
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getPlayerPieces()){
            if (!this.piece.equals(piece) && !this.rook.equals(piece)){
                builder.setPiece(piece);
            }
        }

        // Set Enemy Player pieces on new Board
        placeEnemyPieces(builder);

        // Move King and Rook
        builder.setPiece(this.piece.movePiece(this));
        builder.setPiece(new Rook(this.xDestinationRook, this.yDestinationRook, this.rook.getAlliance(), false));
        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
        return builder.build();
    }
}
