package com.blundell.tut;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class TemperatureConverterApplication extends Application {
    private static final int DECIMAL_PLACES_DEFAULT = 2;
    private static final String KEY_DECIMAL_PLACES = ".KEY_DECIMAL_PLACES";

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void setDecimalPlaces(int places) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_DECIMAL_PLACES, places);
        editor.apply();
    }

    public int getDecimalPlaces() {
        return sharedPreferences.getInt(KEY_DECIMAL_PLACES, DECIMAL_PLACES_DEFAULT);
    }
}
