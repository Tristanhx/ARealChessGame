package com.example.tristan.arealchessgame.chess_engine;

/**
 * Created by Tristan on 10/06/2017.
 */

public class Tools {

    public static final int BOARD_DIM  = 8;
    public static boolean isValid(int xCoorDest, int yCoorDest) {
        return xCoorDest >= 0 && xCoorDest < BOARD_DIM && yCoorDest >= 0 && yCoorDest < BOARD_DIM;
    }
    public static int convertPosition(int xCoordinate, int yCoordinate){
        return BOARD_DIM * yCoordinate + xCoordinate;
    }
}
