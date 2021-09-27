package com.kids.counting.math.games;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferences {
    int value;
    public static final String prefname = "mypref";
    public static final String musc = "getmusic";

    public int getValue(Context context,String key) {
        SharedPreferences settings;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(prefname, Context.MODE_PRIVATE);
        value= settings.getInt(key, 0);
        return value;
    }
    public void saveInt(Context context,String key, int value) {

        SharedPreferences sharedPref = context.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();

    }
}