package com.example.tristan.arealchessgame.ChessEngine;

import android.widget.ImageView;

/**
 * Created by Tristan on 07/06/2017.
 */

public class PositionConverter {

    public PositionConverter(){}

    public int convertPosition(int xCoordinate, int yCoordinate){
        int positionCounter = 0;
        int position = 0;
        for (int i = 0 ;i < 8 ; i++){
            for (int j = 0 ; j < 8 ;j++){
                if (xCoordinate == i && yCoordinate == j){
                    position = positionCounter;
                }
                else{
                    positionCounter++;
                }
            }
        }
        return position;
    }

//    public int convertPosition(int xCoordinate, int yCoordinate){
//        int position = Tools.BOARD_DIM * yCoordinate + xCoordinate;
//        return position;
//    }
}

