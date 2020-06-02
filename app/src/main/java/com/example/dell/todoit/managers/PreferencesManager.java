package com.example.dell.todoit.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dell.todoit.Constants;

public class PreferencesManager {
    private static PreferencesManager ourInstance;
    private SharedPreferences preferences;

    public static PreferencesManager getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new PreferencesManager(context);
        }
        return ourInstance;
    }

    private PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(Constants.PACKAGE, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value){
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String defValue){
        return preferences.getString(key, defValue);
    }
}
