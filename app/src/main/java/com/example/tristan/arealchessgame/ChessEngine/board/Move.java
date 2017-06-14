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
    public static final Move NO_MOVE = new NoMove();

    Move(final Board board, final Piece piece, final int xDestination, final int yDestination){
        this.board = board;
        this.piece = piece;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public int getxDestination(){
        return this.xDestination;
    }

    public int getyDestination(){
        return this.yDestination;
    }

    public abstract Board execute();

    public int getCurrentXPos() {
        return this.piece.getXPos();
    }

    public int getCurrentYPos() {
        return this.piece.getYPos();
    }
}
