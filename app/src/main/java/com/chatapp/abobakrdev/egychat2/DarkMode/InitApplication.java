package com.chatapp.abobakrdev.egychat2.DarkMode;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class InitApplication {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private boolean isNightModeEnabled = false;

    private static InitApplication singleton = null;
    private static Context context ;

    public static InitApplication getInstance(Context context1 ) {

        if(singleton == null)
        {
            singleton = new InitApplication();
            context=context1;
        }
        return singleton;
    }



    public boolean isNightModeEnabled() {
        SharedPreferences mPrefs = context.getSharedPreferences("Theme",MODE_PRIVATE);
        this.isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;

        SharedPreferences mPrefs =context.getSharedPreferences("Theme",MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }
}