package com.sv.memory.activitys;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sv.memory.R;
import com.sv.memory.utils.AppProperties;

/**
 * Created by WP on 06.07.2014.
 */
public class SettingsActivity extends Activity {

    private Handler handler;

    private SoundPool sounds;

    private boolean s;
    private boolean v;
    private boolean a;

    private TextView soundTitle;
    private TextView vibrateTitle;
    private TextView advertisementTitle;

    private ImageView sound;
    private ImageView vibrate;
    private ImageView advertisement;

    private AnimationDrawable soundAnimator;
    private AnimationDrawable vibrateAnimator;
    private AnimationDrawable advertisementAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        handler = new Handler();

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sounds.load(getApplicationContext(), R.raw.click, 1);

        s = AppProperties.getInstance().isSound();
        v = AppProperties.getInstance().isVibrate();
        a = AppProperties.getInstance().isAdvertisement();

        soundTitle = (TextView) findViewById(R.id.soundTitle);
        vibrateTitle = (TextView) findViewById(R.id.vibrateTitle);
        advertisementTitle = (TextView) findViewById(R.id.advertisementTitle);

        sound = (ImageView) findViewById(R.id.sound);

        if(s) {
            sound.setBackgroundResource(R.drawable.a9);
            soundTitle.setText(getString(R.string.sound)+ " [" +getString(R.string.on)+ "]");
        } else {
            soundTitle.setText(getString(R.string.sound)+ " [" +getString(R.string.off)+ "]");
            sound.setBackgroundResource(R.drawable.anim);
        }


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(s) {
                    sound.setBackgroundResource(R.drawable.a1);
                    s = false;
                    soundTitle.setText(getString(R.string.sound)+ " [" +getString(R.string.off)+ "]");
                } else {
                    sound.setBackgroundResource(R.drawable.anim);
                    soundAnimator = (AnimationDrawable) sound.getBackground();
                    soundAnimator.start();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            soundAnimator.stop();
                        }
                    }, 1650);
                    s = true;
                    soundTitle.setText(getString(R.string.sound)+ " [" +getString(R.string.on)+ "]");
                }
            }
        });



        vibrate = (ImageView) findViewById(R.id.vibrate);

        if(v) {
            vibrateTitle.setText(getString(R.string.vibrate)+ " [" +getString(R.string.on)+ "]");
            vibrate.setBackgroundResource(R.drawable.a9);
        } else {
            vibrateTitle.setText(getString(R.string.vibrate)+ " [" +getString(R.string.off)+ "]");
            vibrate.setBackgroundResource(R.drawable.anim);
        }


        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SettingsActivity.this.v) {
                    vibrate.setBackgroundResource(R.drawable.a1);
                    SettingsActivity.this.v = false;
                    vibrateTitle.setText(getString(R.string.vibrate)+ " [" +getString(R.string.off)+ "]");
                } else {
                    vibrate.setBackgroundResource(R.drawable.anim);
                    vibrateAnimator = (AnimationDrawable) vibrate.getBackground();
                    vibrateAnimator.start();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vibrateAnimator.stop();
                        }
                    }, 1650);
                    SettingsActivity.this.v = true;
                    vibrateTitle.setText(getString(R.string.vibrate)+ " [" +getString(R.string.on)+ "]");
                }
            }
        });

        advertisement = (ImageView) findViewById(R.id.advertisement);

        if(a) {
            advertisementTitle.setText(getString(R.string.advertisement)+ " [" +getString(R.string.on)+ "]");
            advertisement.setBackgroundResource(R.drawable.a9);
        } else {
            advertisementTitle.setText(getString(R.string.advertisement)+ " [" +getString(R.string.off)+ "]");
            advertisement.setBackgroundResource(R.drawable.anim);
        }


        advertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a) {
                    advertisement.setBackgroundResource(R.drawable.a1);
                    a = false;
                    advertisementTitle.setText(getString(R.string.advertisement)+ " [" +getString(R.string.off)+ "]");
                } else {
                    advertisement.setBackgroundResource(R.drawable.anim);
                    advertisementAnimator = (AnimationDrawable) advertisement.getBackground();
                    advertisementAnimator.start();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            advertisementAnimator.stop();
                        }
                    }, 1650);
                    a = true;
                    advertisementTitle.setText(getString(R.string.advertisement)+ " [" +getString(R.string.on)+ "]");
                }
            }
        });
    }

    public void ok(View view) {

        if(s) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }

        AppProperties.getInstance().setSound(s);
        AppProperties.getInstance().setVibrate(v);
        AppProperties.getInstance().setAdvertisement(a);
        AppProperties.getInstance().saveProperties();

        finish();
        overridePendingTransition(R.anim.menu_push_down_in, R.anim.menu_push_down_out);
    }

    public void cancel(View view) {

        if(AppProperties.getInstance().isSound()) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }
        finish();
        overridePendingTransition(R.anim.menu_push_down_in, R.anim.menu_push_down_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.menu_push_down_in, R.anim.menu_push_down_out);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
