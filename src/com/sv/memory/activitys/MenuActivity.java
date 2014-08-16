package com.sv.memory.activitys;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import com.sv.memory.R;
import com.sv.memory.service.SoundService;
import com.sv.memory.utils.AppProperties;

public class MenuActivity extends Activity {

    private SoundPool sounds;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AppProperties.getInstance().init(getApplicationContext());

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sounds.load(getApplicationContext(), R.raw.click, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(AppProperties.getInstance().isSound()) {
            startService(new Intent(this, SoundService.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, SoundService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(isFinishing()) {
            stopService(new Intent(this, SoundService.class));
        }
    }

    public void newGame(View view) {
        startActivity(new Intent(this, GameActivity.class));

        if(AppProperties.getInstance().isSound()) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }
    }

    public void option(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
        overridePendingTransition(R.anim.settings_push_down_out, R.anim.settings_push_down_in);

        if(AppProperties.getInstance().isSound()) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }
    }

    public void moreGame(View view) {
        if(AppProperties.getInstance().isSound()) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }
    }

}
