package com.example.tristan.arealchessgame.chess_engine.move;

/**
 * Created by Tristan on 14/06/2017.
 */

public enum MoveWas {
    LEGAL {
        @Override
        public boolean isExecuted() {
            return true;
        }
    },
    ILLEGAL {
        @Override
        public boolean isExecuted() {
            return false;
        }
    }, THIS_LEAVES_KING_IN_CHECK {
        @Override
        public boolean isExecuted() {
            return false;
        }
    };

    public abstract boolean isExecuted();
}
