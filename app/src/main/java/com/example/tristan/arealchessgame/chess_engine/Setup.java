package com.example.tristan.arealchessgame.chess_engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.tristan.arealchessgame.GameChanger;
import com.example.tristan.arealchessgame.chess_engine.player.Player;

/**
 * Created by Tristan on 22/06/2017.
 */

public class Setup {

    private GameChanger.Type whiteType;
    private GameChanger.Type blackType;

    public Setup(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean HvH = preferences.getBoolean("HvH", true);
        Boolean CvC = preferences.getBoolean("CvC", true);
        this.whiteType = CvC ? GameChanger.Type.COMPUTER : GameChanger.Type.HUMAN;
        this.blackType = HvH ? GameChanger.Type.HUMAN : GameChanger.Type.COMPUTER;
    }

    public boolean isComputer(final Player player){
        if (player.getAlliance() == Alliance.WHITE){
            return getWhiteType() == GameChanger.Type.COMPUTER;
        }
        return getBlackType() == GameChanger.Type.COMPUTER;
    }

    private GameChanger.Type getWhiteType(){
        return this.whiteType;
    }

    private GameChanger.Type getBlackType(){
        return this.blackType;
    }
}
