package com.sv.memory.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SV on 05.08.2014.
 */
public class AppProperties {

    private boolean sound;
    private boolean vibrate;
    private boolean advertisement;

    private SharedPreferences preferences;

    private static AppProperties instance;

    private final String MEMORY_FIFA_PREFERENCES = "MEMORY_FIFA_PREFERENCES";

    private final String SOUND = "SOUND";
    private final String VIBRATE = "VIBRATE";
    private final String ADVERTISEMENT = "ADVERTISEMENT";

    private AppProperties() {
        sound = true;
        vibrate = true;
        advertisement = true;
    }

    public void init(Context context) {
        preferences = context.getSharedPreferences(MEMORY_FIFA_PREFERENCES, Context.MODE_PRIVATE);

        if(preferences.contains(SOUND)) {
            sound = preferences.getBoolean(SOUND, true);
        }

        if(preferences.contains(VIBRATE)) {
            vibrate = preferences.getBoolean(VIBRATE, true);
        }

        if(preferences.contains(ADVERTISEMENT)) {
            advertisement = preferences.getBoolean(ADVERTISEMENT, true);
        }
    }

    public void saveProperties() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SOUND, sound);
        editor.putBoolean(VIBRATE, vibrate);
        editor.putBoolean(ADVERTISEMENT, advertisement);

        editor.commit();
    }

    public static AppProperties getInstance() {

        if(instance == null) {
            instance = new AppProperties();
        }

        return instance;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(boolean advertisement) {
        this.advertisement = advertisement;
    }
}
