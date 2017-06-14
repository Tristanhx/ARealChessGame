package com.example.tristan.arealchessgame.ChessEngine.player;

import com.example.tristan.arealchessgame.ChessEngine.board.Move;

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
