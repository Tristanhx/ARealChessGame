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

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public String toString(){
            return "W";
        }
    },
    BLACK {
        @Override
        public int getDir() {
            return 1;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public String toString(){
            return "B";
        }
    };

    public abstract int getDir();

    public abstract boolean isBlack();
}