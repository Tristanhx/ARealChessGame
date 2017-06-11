package com.example.tristan.arealchessgame;

/**
 * Created by Tristan on 05/06/2017.
 */

public enum Alliance {
    WHITE {
        @Override
        public int getDir() {
            return -1;
        }
    },
    BLACK {
        @Override
        public int getDir() {
            return 1;
        }
    };

    public abstract int getDir();
}