package com.example.tristan.arealchessgame.chess_engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tristan.arealchessgame.GameController;
import com.example.tristan.arealchessgame.chess_engine.player.Player;

/**
 * Created by Tristan on 22/06/2017.
 */

public class Setup {

    private GameController.Type whiteType;
    private GameController.Type blackType;
    private int depth;

    public Setup(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean HvH = preferences.getBoolean("HvH", true);
        Boolean CvC = preferences.getBoolean("CvC", true);
        this.depth = Integer.parseInt(preferences.getString("max_depth", "2"));
        this.whiteType = CvC ? GameController.Type.COMPUTER : GameController.Type.HUMAN;
        this.blackType = HvH ? GameController.Type.HUMAN : GameController.Type.COMPUTER;
    }

    public boolean isComputer(final Player player){
        if (player.getAlliance() == Alliance.WHITE){
            return getWhiteType() == GameController.Type.COMPUTER;
        }
        return getBlackType() == GameController.Type.COMPUTER;
    }

    private GameController.Type getWhiteType(){
        return this.whiteType;
    }

    private GameController.Type getBlackType(){
        return this.blackType;
    }

    public int getDepth(){
        return this.depth;
    }
}
