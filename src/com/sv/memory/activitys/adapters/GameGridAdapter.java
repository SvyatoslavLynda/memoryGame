package com.sv.memory.activitys.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sv.memory.R;
import com.sv.memory.activitys.models.GameGridItem;

import java.util.ArrayList;

/**
 * Created by SV on 7/3/2014.
 */
public class GameGridAdapter extends ArrayAdapter<GameGridItem> {

    private Activity activity;
    private int valueFirst;
    private int valueSecond;

    private int heightItem;

    public GameGridAdapter(Activity activity, int textViewResourceId, ArrayList<GameGridItem> data) {
        super(activity, textViewResourceId, data);

        this.activity = activity;
        valueFirst = -1;
        valueSecond = -1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.grid_item, null);

        TextView item = (TextView) view.findViewById(R.id.viewItem);

        if(heightItem != 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) item.getLayoutParams();
            lp.height = heightItem;
            item.setLayoutParams(lp);
        }
        if(getItem(position).isForeground()) {
            item.setText("" +getItem(position).getValue());
        }

        return view;
    }

    public int getValueFirst() {
        return valueFirst;
    }

    public void setValueFirst(int valueFirst) {
        this.valueFirst = valueFirst;
    }

    public int getValueSecond() {
        return valueSecond;
    }

    public void setValueSecond(int valueSecond) {
        this.valueSecond = valueSecond;
    }


    public int getHeightItem() {
        return heightItem;
    }

    public void setHeightItem(int heightItem) {
        this.heightItem = heightItem;
    }

}
