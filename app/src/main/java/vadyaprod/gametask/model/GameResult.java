package vadyaprod.gametask.model;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.TimeZone;


import vadyaprod.gametask.InfoDeserializer;


/**
 * Created by Vadya on 20.09.17.
 */

public class GameResult {
    public int getPrizePoints() {
        return mPrizePoints;
    }

    private int mPrizePoints;

    public int getPenaltyPoints() {
        return mPenaltyPoints;
    }

    private int mPenaltyPoints;

    public String getAppTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(mAppTime);
    }

    private Date mAppTime;


    public String getServerTime() {
        return mServerTime;
    }


    private String mServerTime;

    public GameResult(int prizePoints, int penaltyPoints, Date appTime, String serverTime)
    {
        this.mPrizePoints = prizePoints;
        this.mPenaltyPoints = penaltyPoints;
        this.mAppTime = appTime;
        this.mServerTime = serverTime;
    }
}
