package com.example.tristan.arealchessgame.gui;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.example.tristan.arealchessgame.R;

/**
 * Created by Tristan on 21/06/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    CheckBoxPreference humanVsHuman;
    CheckBoxPreference humanVsComp;
    CheckBoxPreference compVsComp;
    ListPreference difficulty;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        humanVsHuman = (CheckBoxPreference) findPreference("HvH");
        humanVsComp = (CheckBoxPreference) findPreference("HvC");
        compVsComp = (CheckBoxPreference) findPreference("CvC");
        difficulty = (ListPreference) findPreference("max_depth");

        if (humanVsHuman.isChecked()){
            difficulty.setEnabled(false);
        }

        Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener(){
            public boolean onPreferenceChange(Preference preference, Object newValue){
                String key = preference.getKey();
                if(key.equals("HvH")){
                    humanVsHuman.setChecked(true);
                    humanVsComp.setChecked(false);
                    compVsComp.setChecked(false);
                    difficulty.setEnabled(false);
                }
                else if (key.equals("HvC")){
                    humanVsHuman.setChecked(false);
                    humanVsComp.setChecked(true);
                    compVsComp.setChecked(false);
                    difficulty.setEnabled(true);
                }
                else if (key.equals("CvC")){
                    humanVsHuman.setChecked(false);
                    humanVsComp.setChecked(false);
                    compVsComp.setChecked(true);
                    difficulty.setEnabled(true);
                }
                return (Boolean) newValue;
            }
        };
        humanVsHuman.setOnPreferenceChangeListener(onPreferenceChangeListener);
        humanVsComp.setOnPreferenceChangeListener(onPreferenceChangeListener);
        compVsComp.setOnPreferenceChangeListener(onPreferenceChangeListener);
    }
}
