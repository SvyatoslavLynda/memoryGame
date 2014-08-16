package com.sv.memory.activitys;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.sv.memory.R;
import com.sv.memory.adapters.GameGridAdapter;
import com.sv.memory.layout.GameGridLayout;
import com.sv.memory.models.GameGridItem;
import com.sv.memory.tools.GameTimerListener;
import com.sv.memory.tools.Counter;
import com.sv.memory.tools.GameTimer;
import com.sv.memory.utils.AnimationFactory;
import com.sv.memory.utils.AppProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

/**
 * Created by SV on 7/3/2014.
 */
public class GameActivity extends Activity implements AdapterView.OnItemClickListener, GameTimerListener {

    private GridView gameGrid;
    private TextView score;
    private TextView level;
    private TextView textCounter;
    private ProgressBar progressBar;

    private GameGridAdapter adapter;

    private Handler handler;
    private Animation animation;
    private Timer timer;

    private int drawables[] = { R.drawable.c0, R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12,
            R.drawable.c13, R.drawable.c14, R.drawable.c15, R.drawable.c16, R.drawable.c17, R.drawable.c18, R.drawable.c19,
            R.drawable.c20, R.drawable.c21, R.drawable.c22, R.drawable.c23, R.drawable.c24, R.drawable.c25, R.drawable.c26,
            R.drawable.c27, R.drawable.c28, R.drawable.c29, R.drawable.c30, R.drawable.c31 };

    private int map[] = { 2, 3, 4, 5, 6, 8, 10, 12, 15, 16, 18, 20 };

    private SoundPool sounds;

    private Vibrator vibrator;

    private int levelValue;
    private int scoreValue;

    private int bankScore = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        if(AppProperties.getInstance().isSound()) {
            sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            sounds.load(getApplicationContext(), R.raw.click, 1);
            sounds.load(getApplicationContext(), R.raw.whistle, 1);
            sounds.load(getApplicationContext(), R.raw.game_over, 1);
        }

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        handler = new Handler();

        animation = AnimationUtils.loadAnimation(this, R.anim.tap_item_animation);

        gameGrid = (GridView) findViewById(R.id.gameGrid);
        gameGrid.setOnItemClickListener(this);

        score = (TextView) findViewById(R.id.gameScore);
        level = (TextView) findViewById(R.id.gameLevel);

        textCounter = (TextView) findViewById(R.id.textCounter);
        progressBar = (ProgressBar) findViewById(R.id.progressCounter);

        generate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(isFinishing()) {
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterView.getChildAt(i).startAnimation(animation);
                //AnimationFactory.flipTransition((ViewAnimator) adapterView.getChildAt(i), AnimationFactory.FlipDirection.LEFT_RIGHT);
            }
        }, 10);

        final GameGridAdapter tmpAdapter = (GameGridAdapter) adapterView.getAdapter();
        //check on enable
        if(tmpAdapter.getValueFirst()==tmpAdapter.getItem(i).getValue() && tmpAdapter.getItem(i).isForeground()) {
            return;
        }
        if(tmpAdapter.getItem(i).isForeground()) {
            return;
        }

        //=======Ragacod=========

        if(AppProperties.getInstance().isSound()) {
            sounds.play(1, 1.0f, 1.0f, 0, 0, 1.5f);
        }

        tmpAdapter.getItem(i).setForeground(true);
        tmpAdapter.getItem(i).setOpen(true);
        if(tmpAdapter.getValueFirst()==-1) {
            tmpAdapter.setValueFirst(tmpAdapter.getItem(i).getValue());
        } else {
            if(tmpAdapter.getValueSecond()==-1) {
                tmpAdapter.setValueSecond(tmpAdapter.getItem(i).getValue());

                if(tmpAdapter.getValueFirst() != tmpAdapter.getValueSecond()) {
                    gameGrid.setOnItemClickListener(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            for(int j = 0; j<tmpAdapter.getCount(); j++) {
                                if(tmpAdapter.getValueFirst() == tmpAdapter.getItem(j).getValue() || tmpAdapter.getValueSecond() == tmpAdapter.getItem(j).getValue()) {
                                    tmpAdapter.getItem(j).setForeground(false);
                                    tmpAdapter.getItem(j).setOpen(false);
                                }
                            }

                            tmpAdapter.setValueFirst(-1);
                            tmpAdapter.setValueSecond(-1);
                            tmpAdapter.notifyDataSetInvalidated();
                            gameGrid.setOnItemClickListener(GameActivity.this);
                        }
                    }, 500);
                    if(bankScore != 0) {
                        bankScore -= 100;
                    }
                } else {
                    tmpAdapter.setValueFirst(-1);
                    tmpAdapter.setValueSecond(-1);

                    scoreValue += bankScore;
                    score.setText(getResources().getString(R.string.ScoreTitle)+ " " +scoreValue);
                    bankScore = 1000;

                    if(AppProperties.getInstance().isVibrate()) {
                        vibrator.vibrate(1000);
                    }

                    if(AppProperties.getInstance().isSound()) {
                        sounds.play(2, 1.0f, 1.0f, 0, 0, 1.5f);
                    }
                }

            }
        }

        tmpAdapter.notifyDataSetInvalidated();

        for(int j = 0; j<tmpAdapter.getCount(); j++) {

            if(!tmpAdapter.getItem(j).isForeground()) {
                break;
            }

            if(j == tmpAdapter.getCount()-1) {
                Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG).show();
                timer.cancel();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        generate();
                    }
                },1000);
            }
        }
    }

    @Override
    public void onTick(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progress);
                textCounter.setText("" + progress);
            }
        });
    }

    @Override
    public void onFinish() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(AppProperties.getInstance().isVibrate()) {
                    vibrator.vibrate(1000);
                }

                if(AppProperties.getInstance().isSound()) {
                    sounds.play(3, 1.0f, 1.0f, 0, 0, 1.5f);
                }

                gameGrid.setOnItemClickListener(null);
                Toast.makeText(getApplicationContext(), "Game Over!", Toast.LENGTH_SHORT).show();
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }

    public void generate() {

        gameGrid = (GridView) findViewById(R.id.gameGrid);
        gameGrid.setOnItemClickListener(this);

        ((GameGridLayout) findViewById(R.id.gameGridLayout)).setFlagInit(false);
        ArrayList<GameGridItem> list = new ArrayList<GameGridItem>();

        shuffleArray(drawables);

        for(int i = 0; i < map[levelValue]; i++) {
            list.add(new GameGridItem(drawables[i]));
            list.add(new GameGridItem(drawables[i]));
        }

        Collections.shuffle(list);

        if(adapter != null) {
            adapter.clear();
        }

        adapter = new GameGridAdapter(this, R.layout.grid_item, list);
        gameGrid.setAdapter(adapter);

        GameTimer gameTimer = new GameTimer(this);
        timer = new Timer();
        timer.schedule(gameTimer, 100, 1000);

        levelValue++;
        level.setText(getResources().getString(R.string.LevelTitle)+ " " +levelValue);
        score.setText(getResources().getString(R.string.ScoreTitle)+ " " +scoreValue);
    }

    private void shuffleArray(int[] arr)
    {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
