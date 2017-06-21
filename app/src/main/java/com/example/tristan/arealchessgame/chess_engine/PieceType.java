package com.example.tristan.arealchessgame.chess_engine;

/**
 * Created by trist on 6/12/2017.
 */

public enum PieceType {

    BISHOP("B") {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public int getValue() {
            return 3;
        }
    },
    KING("K") {
        @Override
        public boolean isKing() {
            return true;
        }

        @Override
        public int getValue() {
            return Integer.MAX_VALUE;
        }
    },
    KNIGHT("N") {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public int getValue() {
            return 3;
        }
    },
    PAWN("P") {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public int getValue() {
            return 1;
        }
    },
    QUEEN("Q") {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public int getValue() {
            return 9;
        }
    },
    ROOK("R") {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public int getValue() {
            return 5;
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
    public abstract int getValue();
}
