package com.sv.memory.activitys.models;

/**
 * Created by SV on 7/3/2014.
 */
public class GameGridItem {

    private int value;
    private boolean foreground;
    private boolean open;

    public GameGridItem(int value) {
        this.value = value;
        foreground = false;
        open = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isForeground() {
        return foreground;
    }

    public void setForeground(boolean foreground) {
        this.foreground = foreground;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
