package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 06/06/2017.
 */

public abstract class Move {

    final Board board;
    final Piece piece;
    final int xDestination;
    final int yDestination;

    Move(final Board board, final Piece piece, final int xDestination, final int yDestination){
        this.board = board;
        this.piece = piece;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
    }
}
