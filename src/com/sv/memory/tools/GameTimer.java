package com.sv.memory.tools;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.TimerTask;

/**
 * Created by SV on 15.08.2014.
 */
public class GameTimer extends TimerTask {

    private GameTimerListener gameTimerListener;

    private int progress = 99;

    public GameTimer() {
        super();
    }

    public GameTimer(GameTimerListener listener) {
        super();

        this.gameTimerListener = listener;
    }

    @Override
    public void run() {
        progress--;

        if(progress == 0) {
            gameTimerListener.onFinish();
        } else {
            gameTimerListener.onTick(progress);
        }
    }

    public GameTimerListener getGameTimerListener() {
        return gameTimerListener;
    }

    public void setGameTimerListener(GameTimerListener gameTimerListener) {
        this.gameTimerListener = gameTimerListener;
    }

}
