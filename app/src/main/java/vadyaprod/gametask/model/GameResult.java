package vadyaprod.gametask.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;


import vadyaprod.gametask.InfoDeserializer;


/**
 * Created by Vadya on 20.09.17.
 */

public class GameResult {
    static URL url;
    static StringBuilder result;
    static ServerInfo sServerInfo;
    static String resultString;

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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(mServerTime);
    }

    private Date mServerTime;

    public GameResult(int prizePoints, int penaltyPoints, Date appTime)
    {
        this.mPrizePoints = prizePoints;
        this.mPenaltyPoints = penaltyPoints;
        this.mAppTime = appTime;
        initServerTime();
    }
    private void initServerTime(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Data.class, new InfoDeserializer())
                .create();

        try {
            url = new URL("https://abcgames.khorost.net/api/?device=123&ppa=zt_getInfo&app_version=1.5.0070&type=test&lang=ru");
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            resultString = result.toString();
            reader.close();

        } catch ( Exception e) {}

        ServerInfo data = gson.fromJson(resultString, ServerInfo.class);
        Log.v("kek", data.getDate().getTimeStamp());
    }
}
