package com.example.tristan.arealchessgame.ChessEngine;

import android.widget.ImageView;

/**
 * Created by Tristan on 07/06/2017.
 */

public class PositionConverter {

    public PositionConverter(){}

    public int convertPositionX(int position){
        if (position <= 7){
            return 0;
        }
        if (position > 7 && position <= 15){
            return 1;
        }
        if (position > 15 && position <= 23){
            return 2;
        }
        if (position > 23 && position <= 31){
            return 3;
        }
        if (position > 31 && position <= 39){
            return 4;
        }
        if (position > 39 && position <= 47){
            return 5;
        }
        if (position > 47 && position <= 55){
            return 6;
        }
        if (position > 55 && position <= 63){
            return 7;
        }
        return 8;
    }

    public int convertPositionY(int position){
        for (int i = 0 ; i < 8 ; i++){

        }

        return 8;
    }
}

