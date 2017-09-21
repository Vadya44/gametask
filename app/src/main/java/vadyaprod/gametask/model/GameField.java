package vadyaprod.gametask.model;

import android.app.Activity;

/**
 * Created by Vadya on 18.09.17.
 */

public class GameField {
    private int mWidth;
    private int mHeight;
    private boolean mShowServerTime;
    private boolean mShowCoordinates;
    static Activity sActivity;


    public boolean isShowServerTime() {
        return mShowServerTime;
    }

    public void setShowServerTime(boolean showServerTime) {
        mShowServerTime = showServerTime;
    }

    public boolean isShowCoordinates() {
        return mShowCoordinates;
    }

    public void setShowCoordinates(boolean showCoordinates) {
        mShowCoordinates = showCoordinates;
    }


    public GameField(int width, int height, boolean showCoordinates, boolean showServerTime, Activity activity){
        this.mWidth = width;
        this.mHeight = height;
        this.mShowCoordinates = showCoordinates;
        this.mShowServerTime = showServerTime;
        sActivity = activity;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }


    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }


}
