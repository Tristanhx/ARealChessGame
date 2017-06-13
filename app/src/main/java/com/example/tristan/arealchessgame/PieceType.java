package com.example.tristan.arealchessgame;

/**
 * Created by trist on 6/12/2017.
 */

public enum PieceType {

    BISHOP("B"),
    KING("K"),
    KNIGHT("N"),
    PAWN("P"),
    QUEEN("Q"),
    ROOK("R");


    private String pieceName;

    PieceType(final String pieceName){
        this.pieceName = pieceName;
    }

    @Override
    public String toString(){
        return this.pieceName;
    }
}
