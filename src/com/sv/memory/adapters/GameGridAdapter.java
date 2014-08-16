package com.sv.memory.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.sv.memory.R;
import com.sv.memory.models.GameGridItem;
import com.sv.memory.utils.AnimationFactory;

import java.util.ArrayList;

/**
 * Created by SV on 7/3/2014.
 */
public class GameGridAdapter extends ArrayAdapter<GameGridItem> {

    private Activity activity;

    private int valueFirst;
    private int valueSecond;

    private int heightItem;
    private int widthItem;

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

        ImageView item = (ImageView) view;
        /*ViewFlipper item = (ViewFlipper) view;
        ImageView firstImg = (ImageView) view.findViewById(R.id.firstImg);
        ImageView secondImg = (ImageView) view.findViewById(R.id.secondImg);*/

        if(heightItem != 0) {
            item.setLayoutParams(new GridView.LayoutParams(widthItem, heightItem));
            item.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        if(getItem(position).isForeground()) {
            item.setImageResource(getItem(position).getValue());
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

    public int getWidthItem() {
        return widthItem;
    }

    public void setWidthItem(int widthItem) {
        this.widthItem = widthItem;
    }

}
