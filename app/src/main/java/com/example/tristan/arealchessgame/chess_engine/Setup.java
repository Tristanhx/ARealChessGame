package com.example.tristan.arealchessgame.chess_engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tristan.arealchessgame.GameActivity;
import com.example.tristan.arealchessgame.SettingsActivity;
import com.example.tristan.arealchessgame.SettingsFragment;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.Type;

/**
 * Created by Tristan on 22/06/2017.
 */

public class Setup {

    private Type whiteType;
    private Type blackType;
    SharedPreferences preferences;
    String HvH;
    String CvC;
    public Setup(Context context){
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        HvH = preferences.getString("HvH", null);
        CvC = preferences.getString("CvC", null);
        this.whiteType = CvC.equals("true") ? Type.COMPUTER : Type.HUMAN;
        this.blackType = HvH.equals("true") ? Type.HUMAN : Type.COMPUTER;

    }

    public boolean isComputer(final Player player){
        if (player.getAlliance() == Alliance.WHITE){
            return getWhiteType() == Type.COMPUTER;
        }
        return getBlackType() == Type.COMPUTER;
    }

    Type getWhiteType(){
        return this.whiteType;
    }

    Type getBlackType(){
        return this.blackType;
    }
}
