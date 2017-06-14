package com.example.tristan.arealchessgame;

/**
 * Created by trist on 6/12/2017.
 */

public enum PieceType {

    BISHOP("B") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    KING("K") {
        @Override
        public boolean isKing() {
            return true;
        }
    },
    KNIGHT("N") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    PAWN("P") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    QUEEN("Q") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    ROOK("R") {
        @Override
        public boolean isKing() {
            return false;
        }
    };


    private String pieceName;

    PieceType(final String pieceName){
        this.pieceName = pieceName;
    }

    @Override
    public String toString(){
        return this.pieceName;
    }

    public abstract boolean isKing();
}
