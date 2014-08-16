package com.sv.memory.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import com.sv.memory.R;

import java.util.Random;

/**
 * Created by SV on 29.07.2014.
 */
public class SoundService extends Service {

    private static final String TAG = "MyService";
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        if(new Random().nextBoolean()) {
            player = MediaPlayer.create(this, R.raw.bg);
        } else {
            player = MediaPlayer.create(this, R.raw.bg1);
        }
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        player.start();
    }
}
