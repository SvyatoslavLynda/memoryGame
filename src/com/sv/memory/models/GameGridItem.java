package com.sv.memory.models;

/**
 * Created by SV on 7/3/2014.
 */
public class GameGridItem {

    private int value;
    private boolean foreground;
    private boolean open;
    private String res;

    public GameGridItem(int value) {
        this.value = value;
        foreground = false;
        open = false;
        res = "c"+value;
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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
