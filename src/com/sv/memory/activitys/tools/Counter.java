package com.sv.memory.activitys.tools;

import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by SV on 06.07.2014.
 */
public class Counter extends CountDownTimer {


    private ProgressBar progressBar;
    private TextView textCounter;

    private int progress = 100;
    private float count;

    public Counter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        this.count = (float) progress / ((int) millisInFuture/1000);
    }

    @Override
    public void onTick(long l) {
        progress -= count;

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
}
