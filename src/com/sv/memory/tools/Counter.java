package com.sv.memory.tools;

import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by SV on 06.07.2014.
 */
public class Counter extends CountDownTimer {

    private GameTimerListener gameTimerListener;

    private ProgressBar progressBar;
    private TextView textCounter;

    private int progress = 99;

    public Counter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {

        progress -= 1;

        if(progress <=0) {
            progressBar.setProgress(0);
            textCounter.setText("0");
            return;
        }
        progressBar.setProgress(progress);
        textCounter.setText("" + progress);
    }

    @Override
    public void onFinish() {
        if(gameTimerListener !=null) {
            gameTimerListener.onFinish();
        }
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TextView getTextCounter() {
        return textCounter;
    }

    public void setTextCounter(TextView textCounter) {
        this.textCounter = textCounter;
    }

    public GameTimerListener getGameTimerListener() {
        return gameTimerListener;
    }

    public void setGameTimerListener(GameTimerListener gameTimerListener) {
        this.gameTimerListener = gameTimerListener;
    }
}
