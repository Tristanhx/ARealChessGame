package com.example.tristan.arealchessgame;

import android.content.SharedPreferences;

/**
 * Created by Tristan on 27/06/2017.
 */

public class ScoreObject {
    private int whitePlayerScore;
    private int blackPlayerScore;
    private static ScoreObject instance;

    public static ScoreObject getInstance(){
        return instance;
    }

    public static ScoreObject getInstance(int white, int black){
        if (instance == null){
            instance = new ScoreObject(white, black);
        }
        return instance;
    }


    private ScoreObject(int whitePlayerScore, int blackPlayerScore){
        this.whitePlayerScore = whitePlayerScore;
        this.blackPlayerScore = blackPlayerScore;
    }

    public void setBlackPlayerScore(int blackPlayerPoint) {
        this.blackPlayerScore += blackPlayerPoint;
    }

    public void setWhitePlayerScore(int whitePlayerPoint) {
        this.whitePlayerScore = whitePlayerPoint;
    }

    public int getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public int getWhitePlayerScore() {
        return whitePlayerScore;
    }
}
