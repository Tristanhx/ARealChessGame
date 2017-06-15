package com.example.tristan.arealchessgame.ChessEngine.player;

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
    };

    public abstract boolean isExecuted();
}
