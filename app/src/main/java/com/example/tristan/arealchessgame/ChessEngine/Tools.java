package com.example.tristan.arealchessgame.ChessEngine;

/**
 * Created by Tristan on 10/06/2017.
 */

public class Tools {
    public static boolean isValid(int xCoorDest, int yCoorDest) {
        return xCoorDest >= 0 && xCoorDest < 8 && yCoorDest >= 0 && yCoorDest < 8;
    }
}
