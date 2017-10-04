package vadyaprod.gametask.model;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;

import vadyaprod.gametask.InfoDeserializer;
import vadyaprod.gametask.container.GameResultContainer;
import vadyaprod.gametask.ui.GameFragment;

/**
 * Created by Vadya on 20.09.17.
 */

public class GameProcess {
    CountDownTimer timer;
    long countDownPeriod;
    private GameFragment mGameFragment;

    private URL url;
    private StringBuilder result;
    private String resultString;
    private long mUnixTime;

    public void setTextTimer(TextView textTimer) {
        this.textTimer = textTimer;
    }

    private TextView textTimer;
    private TextView textPoints;
    private boolean isShowTime;

    public boolean isActive() {
        return misActive;
    }

    private boolean misActive;

    public int getPrizePoint() {
        return mPrizePoint;
    }

    private int mPrizePoint;

    public int getPenaltyPoint() {
        return mPenaltyPoint;
    }

    private int mPenaltyPoint;

    public void stop(){
        timer.onFinish();
        timer.cancel();
        timer = null;
    }

    public GameProcess(TextView textView, TextView textPoints, GameFragment fragment, boolean isShow){
        countDownPeriod = 30000;
        createCountDownTimer();
        mPrizePoint = 0;
        mPenaltyPoint = 0;
        textTimer = textView;
        this.textPoints = textPoints;
        mGameFragment = fragment;
        isShowTime = isShow;
        misActive = true;
        initServerTime();
        getServerTime();
    }



    public void reduceCountDownTimer(){
        if (timer != null)
        timer.cancel();
        mPenaltyPoint++;
        timer = new CountDownTimer(countDownPeriod - 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (isShowTime)
                    textTimer.setText("\rServer time is: " + getServerTime() + "\n\rTimer " + millisUntilFinished / 1000);
                else
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                textPoints.setText("Points: " + mPrizePoint + " Penalty: " + mPenaltyPoint);
                countDownPeriod=millisUntilFinished;

            }

            @Override
            public void onFinish() {
                Date date = new Date();
                GameResult gameResult = new GameResult(mPrizePoint, mPenaltyPoint,
                        date, getServerTime());
                GameResultContainer.addResult(gameResult);
                mGameFragment.onSwitchToResluts();
                misActive = false;
            }
        };
        timer.start();
    }


    public void createCountDownTimer() {
        if (timer != null)
        timer.cancel();
        mPrizePoint++;
        timer = new CountDownTimer(countDownPeriod + 1000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (isShowTime)
                    textTimer.setText("\rServer time is: " + getServerTime() + "\n\rTimer " + millisUntilFinished / 1000);
                else
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                textPoints.setText("Points: " + mPrizePoint + " Penalty: " + mPenaltyPoint);
                countDownPeriod=millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Date date = new Date();
                GameResult gameResult = new GameResult(mPrizePoint, mPenaltyPoint,
                        date, getServerTime());
                GameResultContainer.addResult(gameResult);
                mGameFragment.onSwitchToResluts();
                misActive = false;
            }
        };
        timer.start();
    }
    private void initServerTime(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
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
                        in.close();

                    } catch (Exception e) {
                    }

                    ServerInfo data = gson.fromJson(resultString, ServerInfo.class);
                    mUnixTime = Long.parseLong(data.getDate().getTimeStamp());
                }catch (Exception e) {
                }
            }
        });
        thread.start();
        thread = null;

    }
    public String getServerTime() {
        initServerTime();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date((long)mUnixTime*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
