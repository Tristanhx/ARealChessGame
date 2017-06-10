package com.example.tristan.arealchessgame.ChessEngine;

/**
 * Created by Tristan on 10/06/2017.
 */

public class Tools {

    public static final int BOARD_DIM  = 8;
    public static boolean isValid(int xCoorDest, int yCoorDest) {
        return xCoorDest >= 0 && xCoorDest < BOARD_DIM && yCoorDest >= 0 && yCoorDest < BOARD_DIM;
    }
}
