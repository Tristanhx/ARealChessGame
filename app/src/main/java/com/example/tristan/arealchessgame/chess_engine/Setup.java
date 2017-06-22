package com.example.tristan.arealchessgame.chess_engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tristan.arealchessgame.GameChanger;
import com.example.tristan.arealchessgame.chess_engine.player.Player;

/**
 * Created by Tristan on 22/06/2017.
 */

public class Setup {

    private GameChanger.Type whiteType;
    private GameChanger.Type blackType;
    SharedPreferences preferences;
    String HvH;
    String CvC;
    public Setup(Context context){
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        HvH = preferences.getString("HvH", null);
        CvC = preferences.getString("CvC", null);
        this.whiteType = CvC.equals("true") ? GameChanger.Type.COMPUTER : GameChanger.Type.HUMAN;
        this.blackType = HvH.equals("true") ? GameChanger.Type.HUMAN : GameChanger.Type.COMPUTER;

    }

    public boolean isComputer(final Player player){
        if (player.getAlliance() == Alliance.WHITE){
            return getWhiteType() == GameChanger.Type.COMPUTER;
        }
        return getBlackType() == GameChanger.Type.COMPUTER;
    }

    GameChanger.Type getWhiteType(){
        return this.whiteType;
    }

    GameChanger.Type getBlackType(){
        return this.blackType;
    }
}
