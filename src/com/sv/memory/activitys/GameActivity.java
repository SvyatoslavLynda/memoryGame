package com.sv.memory.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import com.sv.memory.R;
import com.sv.memory.activitys.adapters.GameGridAdapter;
import com.sv.memory.activitys.models.GameGridItem;
import com.sv.memory.activitys.tools.Counter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by SV on 7/3/2014.
 */
public class GameActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView gameGrid;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        handler = new Handler();
        gameGrid = (GridView) findViewById(R.id.gameGrid);
        ArrayList<GameGridItem> list = new ArrayList<GameGridItem>();

        for(int i = 0; i < 6; i++) {
            list.add(new GameGridItem(i));
            list.add(new GameGridItem(i));
        }

        Collections.shuffle(list);

        gameGrid.setNumColumns(3);
        gameGrid.setColumnWidth(80);
        gameGrid.setVerticalSpacing(5);
        gameGrid.setHorizontalSpacing(5);
        gameGrid.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);

        gameGrid.setAdapter(new GameGridAdapter(this, R.layout.grid_item, list));
        gameGrid.setOnItemClickListener(this);

        Counter counter = new Counter(30*1000, 1000);
        counter.setProgressBar((ProgressBar) findViewById(R.id.progressCounter));
        counter.setTextCounter((TextView) findViewById(R.id.textCounter));
        counter.start();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        final GameGridAdapter tmpAdapter = (GameGridAdapter) adapterView.getAdapter();
        //check on enable
        if(tmpAdapter.getValueFirst()==tmpAdapter.getItem(i).getValue() && tmpAdapter.getItem(i).isForeground()) {
            return;
        }
        if(tmpAdapter.getItem(i).isForeground()) {
            return;
        }
        //================

        tmpAdapter.getItem(i).setForeground(true);
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

                                }
                            }
                            tmpAdapter.setValueFirst(-1);
                            tmpAdapter.setValueSecond(-1);
                            tmpAdapter.notifyDataSetInvalidated();
                            gameGrid.setOnItemClickListener(GameActivity.this);
                        }
                    }, 2000);

                } else {
                    tmpAdapter.setValueFirst(-1);
                    tmpAdapter.setValueSecond(-1);
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
            }
        }
    }
}
