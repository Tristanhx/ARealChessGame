package com.example.tristan.arealchessgame.ChessEngine;

import android.widget.ImageView;

/**
 * Created by Tristan on 07/06/2017.
 */

public class PositionConverter {

    public PositionConverter(){}

    public int convertPosition(int xCoordinate, int yCoordinate){

        return Tools.BOARD_DIM * yCoordinate + xCoordinate;
    }
}

