package com.sv.memory.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.sv.memory.adapters.GameGridAdapter;

/**
 * Created by WP on 08.07.2014.
 */
public class GameGridLayout extends LinearLayout {

    private int widthGridItem;
    private int heightGridItem;

    private int spaceWidth;
    private int spaceHeight;

    private int countColumn;
    private int countRow;

    private int ballRadius;

    private boolean flagInit = false;

    public GameGridLayout(Context context) {
        super(context);
        init();
    }

    public GameGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(!flagInit)
            init();

    }

    private void init() {
        GridView gridView = (GridView) super.getChildAt(0);

        if(gridView==null) {
            return;
        }
        if(gridView.getAdapter() == null) {
            return;
        }

        flagInit = true;

        gridView.setEnabled(true);
        int length = gridView.getAdapter().getCount();
        int width = this.getWidth();
        int height = this.getHeight();
        this.
        calculateGameGridSize(length);

        if(width < height) {
            this.spaceWidth = width / ballRadius  * (countColumn);
            this.widthGridItem = (width - (countColumn+1) * this.spaceWidth)/countColumn;
            this.heightGridItem = this.widthGridItem;
            this.spaceHeight = (height - countRow*this.heightGridItem)/countRow+1;
        } else {
            this.spaceHeight = height / ballRadius * (countRow+1);
            this.heightGridItem = (height - (countRow+1) * this.spaceHeight)/countRow;
            this.widthGridItem = this.heightGridItem;
            this.spaceWidth = (width - countColumn*this.widthGridItem)/countColumn+1 ;
        }


        ((GameGridAdapter) gridView.getAdapter()).setHeightItem(this.heightGridItem);
        ((GameGridAdapter) gridView.getAdapter()).setWidthItem(this.widthGridItem);

        gridView.setNumColumns(this.countColumn);
        gridView.setColumnWidth(this.widthGridItem);
        gridView.setVerticalSpacing(this.spaceHeight);
        gridView.setHorizontalSpacing(this.spaceWidth);
        //gridView.setStretchMode(GridView.STRETCH_SPACING);

        ((GameGridAdapter) gridView.getAdapter()).notifyDataSetChanged();
    }

    private void calculateGameGridSize(int length) {

        switch (length) {
            case 4:
                countColumn = countRow = 2;
                ballRadius = 16;
                break;
            case 6:
                countColumn = 2;
                countRow = 3;
                ballRadius = 16;
                break;
            case 8:
                countColumn = 2;
                countRow = 4;
                ballRadius = 16;
                break;
            case 10:
                countColumn = 2;
                countRow = 5;
                ballRadius = 12;
                break;
            case 12:
                countColumn = 3;
                countRow = 4;
                ballRadius = 64;
                break;
            case 16:
                countColumn = countRow = 4;
                ballRadius = 120;
                break;
            case 20:
                countColumn = 4;
                countRow = 5;
                ballRadius = 120;
                break;
            case 24:
                countColumn = 4;
                countRow = 6;
                ballRadius = 120;
                break;
            case 30:
                countColumn = 5;
                countRow = 6;
                ballRadius = 120;
                break;
            case 32:
                countColumn = 4;
                countRow = 8;
                ballRadius = 64;
                break;
            case 36:
                countColumn = 6;
                countRow = 6;
                ballRadius = 120;
                break;
            case 40:
                countColumn = 5;
                countRow = 8;
                ballRadius = 64;
                break;
            default:
                countColumn = countRow = 2;
                ballRadius = 16;
        }
    }

    public boolean isFlagInit() {
        return flagInit;
    }

    public void setFlagInit(boolean flagInit) {
        this.flagInit = flagInit;
    }
}
